@Interaction
Feature: Interaction Mouse Cursor
  Background:
    Given I am on Interaction Test page

  @Sortable
  Scenario: Sort element
    Given I am on Sortable Test page
    Then I sort element on List tab
    And I sort element on Grid tab

  @Selectable
  Scenario: Selectable element
    Given I am on Selectable Test page
    Then I select element on List tab
    And I select element on Grid tab

  @Droppable
  Scenario: Drag and drop element
    Given I am on Droppable Test page
    Then I drag and drop element on Simple tab
    And I drag and drop element on Accept tab

  @Draggable
    Scenario: Drag element
    Given I am on Draggable Test page
    Then I drag element on Simple tab
    And I drag element on Axis tab
    And I drag element on Container tab

