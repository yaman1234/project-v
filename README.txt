UI Automation - Automate Daraz login and search using Java, Selenium, and the Page Object Model (POM) design pattern.
The project developed is supposed to accomplish following objectives:

1. Open Daraz Website : https://www.daraz.com.np/

2. Create a test that automatically logs into Daraz using a valid username and password retrieved
from an SQL database (create a dummy database -sqlite holding necessary attributes). This test
should try logging in multiple times using various sets of credentials, but it shouldn't start with
the valid ones. Instead, it should try to log in with invalid credentials until it successfully logs in
with the valid one. For instance, if there are 5 datasets, the first 4 should log in unsuccessfully
and the last one should successfully log in.

3. Develop a test case to check search suggestions on Daraz by typing "Sam" in the search box.
Ensure that all the suggestions starting with "Sam" are displayed in the suggestion box.
Then, click on the fourth suggestion from the top and add an item to the cart with a specific
price (for example: Rs. 28,900).

4. Develop an additional test case to determine the remaining time for the flash sale. If the flash
sale concludes within three hours, add the first item to the cart. If the remaining time falls
between three and five hours, include the second item in the cart. Lastly, if the remaining time
exceeds five hours, add the third item to the cart. Ensure to print the name and position of each
item before adding it to the cart.

5. Generate a new test case where you navigate to the "Sneakers" category and record the name
and price of the first 20 pair of sneakers into an Excel sheet named "ShoeList.csv".
Then, add the first item containing name “Air Force 1” with size 43 and quantity 2 to the cart.

6. Provide a README.md file explaining how to set up the project, run the tests, and any other
relevant information.





------------------------------------------------------------------------------------------------
1. Add Required Dependencies

2. Add TestNG library
