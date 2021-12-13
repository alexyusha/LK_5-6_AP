package spring.dao;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import spring.model.Contract;
import spring.model.InsuredPerson;
import spring.service.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InsuredPersonDao extends CRUD<InsuredPerson> {
    private static final String CREATE = "INSERT INTO insured_people(first_name, last_name, middle_name, INN, price, number_contract) VALUES(?, ? ,?, ?, ?, ?)";
    private static final String READ = "SELECT first_name, last_name, middle_name, INN, price, number_contract FROM insured_people WHERE INN = ?";
    private static final String UPDATE = "UPDATE insured_people SET first_name = ?, last_name = ?, middle_name = ?, INN = ?, price = ?, number_contract = ? WHERE INN = ?";
    private static final String DELETE = "DELETE FROM insured_people WHERE INN = ?";
    private static final String SELECT_CHECK = "SELECT id, INN FROM insured_people WHERE INN = ?";
    private static final String ALL_PEOPLE = "SELECT first_name, last_name, middle_name, INN, price, number_contract FROM insured_people";


    public long create(InsuredPerson person) {
        long id = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;

        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            if (checkINN(person.getInn()) != null) {
                setValue(preparedStatement, person);
                preparedStatement.executeUpdate();
                set = preparedStatement.getGeneratedKeys();
                set.next();
                id = set.getInt(1);
            } else {
                id = getId(person.getInn(), SELECT_CHECK, "inn");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, set);
        }
        return id;
    }

    public InsuredPerson read(String INN) {
        InsuredPerson person = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setString(1, INN);
            set = preparedStatement.executeQuery();
            while (set.next()) {
                person = new InsuredPerson(set.getString("first_name")
                        ,set.getString("last_name")
                        , set.getString("middle_name")
                        , set.getString("inn")
                        , set.getDouble("price")
                        , set.getString("number_contract"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, set);
        }
        return person;
    }

    public void update(InsuredPerson person) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            if (checkINN(person.getInn()) == null){
                connection = ConnectionDB.getConnection();
                preparedStatement = connection.prepareStatement(UPDATE);
                setValue(preparedStatement, person);
                preparedStatement.setString(7, person.getInn());
                preparedStatement.executeUpdate();
            }
            else{
                create(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, null);
        }
    }

    public void delete(String INN) {
        delete(INN, DELETE);
    }

    public void createAll(Contract contract) {
        for (InsuredPerson person : contract.getInsuredPersons()) {
            create(person);
        }
    }

    public List<InsuredPerson> readAll(Contract contract) {
        String number = contract.getNumber();
        List<InsuredPerson> contractPeople = new ArrayList<>();
        List<InsuredPerson> list = getInsuredPerson();
        for (InsuredPerson person : list){
            if (person.getNumberContract().equals(number)){
                contractPeople.add(person);
            }
        }
        return contractPeople;
    }

    public void updateAll(Contract contract) {
        for (InsuredPerson person : contract.getInsuredPersons()) {
            update(person);
        }
    }

    public void deleteAll(Contract contract) {
        for (InsuredPerson person : contract.getInsuredPersons()) {
            delete(person.getInn());
        }
    }

    String checkINN(String INN) {
        return checkUniqueness(INN, SELECT_CHECK, "inn");
    }

    @SneakyThrows
    protected void setValue(PreparedStatement preparedStatement, InsuredPerson person) {
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setString(3, person.getMiddleName());
        preparedStatement.setString(4, person.getInn());
        preparedStatement.setDouble(5, person.getPrice());
        preparedStatement.setString(6, person.getNumberContract());
    }

    @SneakyThrows
    public List<InsuredPerson> getInsuredPerson(){
        List<InsuredPerson> list = new ArrayList<>();
        InsuredPerson person;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try{
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(ALL_PEOPLE);
            set = preparedStatement.executeQuery();

            while (set.next()) {
                person = new InsuredPerson(set.getString("first_name")
                        ,set.getString("last_name")
                        , set.getString("middle_name")
                        , set.getString("inn")
                        , set.getDouble("price")
                        , set.getString("number_contract"));
                list.add(person);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(preparedStatement, connection, set);
        }

        return list;
    }
}
