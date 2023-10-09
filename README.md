# AstonTest - testing bank api for aston
  swagger documentation link http://localhost:8080/swagger-ui/index.html#/account-controller

#En
API Description for Bank Account Management:
  This API allows to work with bank accounts, create new accounts, get information about accounts, and perform operations for transfers and deposits.

Technologies:
  Java 17
  Spring Boot 3.1.4
  Maven 4.0.0
  PostgresSQL

API Usage:

1) Get a list of all accounts
  GET /account
  Returns a list of all bank accounts.

2) Create a new account
  POST /account/new
  Creates a new bank account
  Returns id of new acount.

4) Get information about an account by ID
  GET /account/{id}
  Search account by it's id
  Returns information in JSON format if it's exists or NotFound.
  

6) Get a list of transactions by account ID
  GET /account/{id}/transaction
  Search for a list of transactions for a bank account by its ID
  Returns information in JSON format if it's exists or NotFound.

8) Deposit funds into an account
  PUT /account/transaction/deposit
  Deposits the specified amount into a bank account
  Returns status of transaction.

10) Withdraw funds from an account
  PUT /account/transaction/withdraw
  Withdraws the specified amount from a bank account
  Returns status of transaction.

12) Transfer funds between accounts
  PUT /account/transaction/transfer
  Transfers the specified amount from the sender's account to the recipient's account
  Returns status of transaction.

#RU
Описание API для работы с банковскими счетами:
  Данный API позволяет работать с банковскими счетами, создавать новые счета, получать информацию о счетах и производить операции по переводам и пополнению.

Технологии:
  Java 17
  Spring Boot 3.1.4
  Maven 4.0.0
  PostgresSQL

Использование API:

1) Получение списка всех счетов
  GET /account
  Возвращает список всех банковских счетов.

2) Создание нового счета
  POST /account/new
  Создает новый банковский счет.

3) Получение информации о счете по ID
  GET /account/{id}
  Возвращает информацию о банковском счете по его ID.

4) Получение списка транзакций по ID счета
  GET /account/{id}/transaction
  Возвращает список транзакций для банковского счета по его ID.

5) Пополнение счета
  PUT /account/transaction/deposit
  Пополняет банковский счет на указанную сумму.

6) Снятие со счета
  PUT /account/transaction/withdraw
  Снимает со счета указанную сумму.

7) Перевод между счетами
  PUT /account/transaction/transfer
  Переводит указанную сумму со счета отправителя на счет получателя.
