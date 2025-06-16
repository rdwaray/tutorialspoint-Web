@Element
Feature: Element
  Background:
    Given I am on the Elements Test page

  @TextBox
  Scenario: Input text box
    Given I am on the Text Box test page
    Then I fill Full Name
    And I fill Email
    And I fill Current Address
    When I fill Password
    Then I click on Submit button

  @CheckBox
  Scenario: Check box interaction
    Given I am on the Check Box test page
    Then I click on Main Level One
    When I click on Sub Level One and Two
    Then I click on all Main Level One check box

    And I click on Main Level Two
    When I click on Sub Level Three and Four
    Then I click on all Main Level Two check box

  @RadioButton
    Scenario: Radio Button interaction
    Given I am on the Radio Button test page
    Then I click on Yes radio button
    And I click on Impressive radio button
    And I check Disabled radio button

  @Button
  Scenario: Left and right click input method
    Given I am on Buttons test
    When I click on Double Click Me button
    Then I should see input double click message
    And I click on Right Click Me button
    And I click on Click Me button
    Then I should see input click message

  @Links
  Scenario: Interaction with links element dan windows tab
    Given I am on Links test
    When I click on Home link
    And I go back to maintab
    Then I click on Dynamic Home  link
    And I go back to maintab

    When I click on Created link
    Then I click on No Content
    And I click on Moved link
    And I click on Bad Request link
    And I click on Unauthorized link
    And I click on Forbidden link
    And I click on Not Found link

  @ValidImageandLinks
  Scenario: Find the broken images and link
    Given I am on Broken link image and link
    Then I validate image and link

  @DownloadandUpload
  Scenario: Download and upload file
    Given I am on Upload and Download test page
    When I click on download button
    Then I click on upload button

  @DynamicProperties
  Scenario: Find dynamic element properties
    Given I am on Dynamic Properties test page
    When I wait 5 second until button interactable
