

### Start Here!
### Do the Endpoints according to the order of the Document. 

### API Enpoints
#### http://localhost:{port} at the beginning of the url. 
### 1. Create Customer
- **URL**: /customers?name=Anna%20Larsson&phone=0701234567
- **Method**:'POST'

### 2. Get CustomerID By Name
- **URL**: /customer-id?name=Anna%20Larsson
- **Method**:'GET'

### 3. Create Vehicle
- **URL**: /vehicles?registrationNumber=JKL012&brand=Volkswagen&model=Golf&productionYear=2018
- **Method**:'POST'

### 4. Connect Vehicle To Customer
- **URL**: /customer/1/vehicles/1
- **Method**:'POST'

### 5. Get Customers & Vehicles
- **URL**: /customers
- **Method**:'GET'

### 6. Get Vehicles
- **URL**: /vehicles
- **Method**:'GET'

### 7. Get Vehicles By Brand
- **URL**: /vehicles_by_brand?brand=Volkswagen
- **Method**:'GET'

### Attention!:
If you want to do the endpoints all over again,  
or any error would occur use this:

- **URL**: /clear-database
- **Method**:'DELETE'

*It clears the database and resets the ID to 1.*   
*Then start from the top of the document again.*