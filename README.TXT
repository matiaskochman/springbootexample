
For the solution, I chose to use springboot. An architecture of 3 layers (Dao, Service, Controller). 
For caching I used EHcache to cache the api calls and ShallowEtagHeaderFilter for Caching in the browser.
To run the application, there is a Main.java file wich has a public void main(String[] args) method. 
It will run in the port 8080.

example: localhost:8080/api/recent_purchases/Nat.Koch90

The model is defined by User, Product and Purchase classes. For the solution I created
a ProductAggregated class in the composite package.