package spring.dao;


import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import spring.model.Contract;
import spring.model.InsuredPerson;
import spring.service.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ContractDao extends CRUD<Contract> {

    private static final String CREATE = "INSERT INTO contracts(number) VALUES (?)";
    private static final String READ = "SELECT id, number FROM contracts WHERE number = ?";
    private static final String UPDATE = "UPDATE contracts SET number = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM contracts WHERE number = ?";
    private static final String SELECT_CHECK = "SELECT id ,number FROM contracts WHERE number = ?";

    public long create(Contract contract) {
        long id = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;

        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            if (checkFullNumber(contract.getNumber()) != null){

                preparedStatement.setString(1, contract.getNumber());
                preparedStatement.executeUpdate();

                set = preparedStatement.getGeneratedKeys();
                set.next();
                id = set.getInt(1);
            }else{
                id = getId(contract.getNumber(),SELECT_CHECK , "number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, set);
        }
        return id;
    }

    public Contract read(String number){
        Contract contract = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setString(1, number);
            set = preparedStatement.executeQuery();
            while (set.next()) {
                contract = new Contract(set.getLong("id"), number, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, set);
        }

        return contract;
    }

    public void update(Contract contract){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, contract.getNumber());
            preparedStatement.setLong(2, contract.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, null);
        }
    }

    public void delete(String number){
        delete(number, DELETE);
    }
    protected  String checkFullNumber(String number){
        return checkUniqueness(number, SELECT_CHECK, "number");
    }

    @SneakyThrows
    public List<Contract> getContracts(){
       List<Contract> list = new ArrayList<>();
        String sqlForContract = "SELECT id, number FROM contracts";
        Contract contract = null;
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlForContract);
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()) {
            contract = new Contract(set.getLong("id"), set.getString("number"), null);
            list.add(contract);
        }
        ConnectionDB.closeConnection(preparedStatement, connection, set);

        return list;
    }

}
