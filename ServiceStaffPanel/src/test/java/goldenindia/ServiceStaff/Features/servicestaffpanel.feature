Feature: Service Staff Panel: Logging Out After Completing Orders

  Background: 
    Given I launch the service staff application

  @Regression @Managecourse @Course
  Scenario Outline: Adding and Firing Courses
    And I am logged in to the service staff panel with Staff ID "<Staff ID>" and PIN Code "<PIN Code>"
    Then I clock in to the service staff panel
    And I have selected a table <Table Number> to serve
    When I add courses
    And I add products to the courses
    Then I sent the items to the kitchen.
    And I fire the courses
    Then I should receive an error message

    Examples: 
      | Staff ID | PIN Code | Table Number |
      |     2229 |     1234 | T5           |

  @Regression @TransferProduct @Course
  Scenario Outline: Transferring Products to Another Course
    And I am logged in to the service staff panel with Staff ID "<Staff ID>" and PIN Code "<PIN Code>"
    Then I clock in to the service staff panel
    And I have selected a table <Table Number> to serve
    When I add courses
    And I add products to the courses
    When I edit a product
    And I choose to transfer to another course
    And I check whether product transfer to the another course or not

    Examples: 
      | Staff ID | PIN Code | Table Number |
      |     2229 |     1234 | T5           |

  @Regression @TransferCourse @Course
  Scenario Outline: Transferring Courses
    And I am logged in to the service staff panel with Staff ID "<Staff ID>" and PIN Code "<PIN Code>"
    Then I clock in to the service staff panel
    And I have selected a table <Table Number> to serve
    When I add courses
    And I add products to the courses
    And I transfer a course <Course> to another
    Then I ensure all products are transferred

    Examples: 
      | Staff ID | PIN Code | Table Number | Course   |
      |     2229 |     1234 | T5           | Course 1 |

  @Regression @ClearItemsOfCourse @Course
  Scenario Outline: Clearing Items in Courses
    And I am logged in to the service staff panel with Staff ID "<Staff ID>" and PIN Code "<PIN Code>"
    Then I clock in to the service staff panel
    And I have selected a table <Table Number> to serve
    When I add courses
    And I add products to the courses
    And I edit the course <Course>
    Then I clear all items
    Then I ensure all products are removed

    Examples: 
      | Staff ID | PIN Code | Table Number | Course   |
      |     2229 |     1234 | T5           | Course 1 |

  @Regression @ReprintingCourse @Course
  Scenario Outline: Reprinting the course.
    And I am logged in to the service staff panel with Staff ID "<Staff ID>" and PIN Code "<PIN Code>"
    Then I clock in to the service staff panel
    And I have selected a table <Table Number> to serve
    When I add courses
    And I add products to the courses
    Then I sent the items to the kitchen.
    And I click on the edit option to reprint the course <Course> details.

    Examples: 
      | Staff ID | PIN Code | Table Number | Course   |
      |     2229 |     1234 | T5           | Course 1 |

  Scenario Outline: Staff Logs Out After Completing Orders
    And I am logged in to the service staff panel with Staff ID "<Staff ID>" and PIN Code "<PIN Code>"
    Then I clock in to the service staff panel
    And I have selected a table <Table Number> to serve
    When I add courses
    And I send the order
    Then the items should be sent successfully
    And I proceed to payment
    And I complete the payment process
    Then I log out
    And I generate a daily summary by selecting the email option and printing
    Then a pop-up should appear confirming the action

    Examples: 
      | Staff ID | PIN Code | Table Number |
      |     2229 |     1234 | T5           |
