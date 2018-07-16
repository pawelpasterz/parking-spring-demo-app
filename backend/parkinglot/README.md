# Parking Lot Service demo

Parking lot REST server implementation. Simple demo, only backend. Spring Boot is used.

## Assumptions
1. Only 10 spots (can be changed in `application.properties`).
2. Two driver types (`VIP` and `regular`).
3. Different fees for both types.

## Running demo
1. Mysql is installed.
2. Run `start.sh` script.
3. Enjoy!

### `start.sh` script steps
1. Cleans current application compiled files
2. Creates database for demo purposes
3. Starts application