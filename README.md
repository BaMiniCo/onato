# User Management Application

This is a Spring Boot application for managing Admin and Customer users.

## Requirements

- Java 17
- Maven

## REST Endpoints
### Authentication

- **POST /api/authenticate**: Authenticate and obtain a JWT token.

### Admin Endpoints

- **POST /api/admin**: Create a new admin.
- **PUT /api/admin/password**: Update own password.
- **GET /api/customers**: List all customers.
- **DELETE /api/customer/{customerId}**: Delete a customer by ID.

### Customer Endpoints

- **POST /api/customer**: Create a new customer.
- **PUT /api/customer/address**: Update own address.
- **PUT /api/customer/password**: Update own password.
- **GET /api/customer/me**: Fetch own details.


## Example Usage with Postman
### To use these endpoints with Postman, follow these steps:

1. Authenticate (Login)

Send a POST request to http://54.234.80.148:8080/api/authenticate with the login details.
The response will include a JWT token, which should be used in the Authorization header for subsequent requests:

Authorization: Bearer <jwt_token>

- Create Admin
Ensure you are logged in as an existing ADMIN and use the JWT token for authorization.
Send a POST request to http://54.234.80.148:8080/api/admins with the new admin details.

- Update Own Password (Admin)
Ensure you are logged in as an ADMIN and use the JWT token for authorization.
Send a PUT request to http://54.234.80.148:8080/api/admins/password with the new password.

- List Customers (Admin)
Ensure you are logged in as an ADMIN and use the JWT token for authorization.
Send a GET request to http://54.234.80.148:8080/api/customers to fetch the list of customers.

- Delete Customer (Admin)
Ensure you are logged in as an ADMIN and use the JWT token for authorization.
Send a DELETE request to http://54.234.80.148:8080/api/customers/{customerId} to delete a specific customer.

- Create Customer (Admin)
Ensure you are logged in as an ADMIN and use the JWT token for authorization.
Send a POST request to http://54.234.80.148:8080/api/customers with the new customer details.

- Update Own Address (Customer)
Ensure you are logged in as a CUSTOMER and use the JWT token for authorization.
Send a PUT request to http://54.234.80.148:8080/api/customers/address with the new address.

- Update Own Password (Customer)
Ensure you are logged in as a CUSTOMER and use the JWT token for authorization.
Send a PUT request to http://54.234.80.148:8080/api/customers/password with the new password.

- Fetch Own Details (Customer)
Ensure you are logged in as a CUSTOMER and use the JWT token for authorization.
Send a GET request to http://54.234.80.148:8080/api/customers/me to fetch your own details



