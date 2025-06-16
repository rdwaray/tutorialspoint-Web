@AlertFrameWindow
Feature: Alert, Frame, and Windows
  Background:
    Given I am on the Alert, Frame, and Windows test page


  @BrowserWindows
  Scenario: Navigation window browser
  Given I am on browser windows test page
  Then I click on New Tab button
  And I should be in different windows and back to maintab

  When I click on New Window
  Then I should be in new window browser

  And  I click on New Windows Message
  Then I should be in new windows message


  @Alerts
  Scenario: Interactions with browser alerts
    Given I am on Alert test page
    Then I click on first alert and click OK
    And I click on second alert, wait for five seconds then click OK
    And I click on confirm alert
    And I click prompt box alert and fill "Ray"

  @Iframe
  Scenario: Switch between frames in HTML page
    Given I am on Frames test page
    Then I switch to iframe one
    And I switch to iframe two

  @ModalDialogs
  Scenario: Interactions with small and large Modal
    Given I am on Modal Dialogs test page
    Then I click on Small Modal Button
    And I extract Small Modal text
    And Close Small Modal prompt
    Then I click on Large Modal Button
    And I extract Large Modal text
    And Close Large Modal prompt

