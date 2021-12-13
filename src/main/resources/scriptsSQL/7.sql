SELECT DISTINCT
  product.maker,
  pc.ram
FROM product
  LEFT JOIN pc
    ON product.model = pc.model
