@Widgets
  Feature: Widgets Interaction
    Background:
      Given I am on Widgets Test page

    @Accordian
    Scenario: Accordian Interactions
      Given I am on Accordian Test Page
      Then I click on first Accordian
      And I click on second Accordian
      When I click on third Accordian
      Then I close all Accordian

  @AutoComplete
  Scenario: Auto Complete Form Box
    Given I am on Auto Complete Test Page
    Then I fill form with "Action"
    And I choose "ActionScript"

  @DatePicker
  Scenario: Input date and time
    Given I am on Date Picker Test page
    Then I input Date
    And I input date and time

  @Slider
  Scenario: Drag hold slider
    Given I am on Slider Test Page
    Then I am changing the value of slider

  @ProgressBar
  Scenario: Progress bar interctions
    Given I am on Progress Bar Test page
    Then I click on start progress button
    And I extract value on progres bar

  @Tabs
  Scenario: Tabs Interactions
    Given I am on Tab Test Page
    Then I click on Home tab
    And I click on Profile tab
    And I click on Contact tab

  @MouseHover
  Scenario: Hovering Mouse
    Given I am on Tools Tips Test page
    Then I hover mouse to element one
    And I hover mouse to element two
    And I hover mouse to element three
    And I hover mouse to element four

  @MenuHover
  Scenario: Hovering mouse through menu
    Given I am on Menu Test page
    Then I am hovering to Dropdown menu

  @SelectMenuDropdown
  Scenario: Drop down menu Interaction
    Given I am on Select Menu Test page
    Then I interact with Select One menu


    @Scroll
    Scenario: Scroll to footer and header
      Given I am on Scroll Top test page
      And I scroll header
      Then I am on Scroll Down test page
      And I scroll to footer

