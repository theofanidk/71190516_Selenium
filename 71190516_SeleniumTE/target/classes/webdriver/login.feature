Feature: feature to test login
  Scenario Outline: check login
    Given browser terbuka
    And user berada di halaman login
    When user memasukkan <email> and <password>
    And tombol login diklik
    Then user ke direct ke halaman utama
    Examples:
      |email                | password    |
      |                     |             |
      |                     | joyce12345   |
      |joyce@gmail.com   | joyce1342     |
      |joyce@gmail.com   | joyce1234    |
      |joyce@gmail.com   |             |
      |joyce@gmail.com   | joyce1234567891234 |
      |joyce@gmail.com   | joyce1234**    |

