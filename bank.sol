// SPDX-License-Identifier: MIT
pragma solidity ^0.8.34;

contract SimpleBank {
    address public owner;
    uint256 public balance;

     constructor() public 
     {
        owner = msg.sender;
        balance = 0;
    }

    function deposit(uint256 amount) public {
        require(msg.sender == owner, "Only the owner can deposit money");
        balance += amount;
    }

    function withdraw(uint256 amount) public {
        require(msg.sender == owner, "Only the owner can withdraw money");
        require(amount <= balance, "Insufficient balance");
        balance -= amount;
    }

    function getBalance() public view returns (uint256) {
        return balance;
    }
}

/*
Important Solidity Keywords for Viva
Keyword	Meaning
contract	Defines a smart contract
address	Stores an Ethereum address
uint256	Stores a 256-bit unsigned integer
public	Allows public access
constructor	Executes once during deployment
msg.sender	Address of the caller
require()	Checks a condition
view	Reads data without modifying state
returns	Specifies the return type
return	Returns a value


5. Important Viva Questions and Answers
Q1. What is Solidity?

Solidity is a high-level, object-oriented programming language used to develop smart contracts on Ethereum.

Q2. What is a smart contract?

A smart contract is a self-executing program stored on a blockchain that automatically executes predefined conditions.

Q3. What is a constructor?

A constructor is a special function that executes only once when a smart contract is deployed.

Q4. What is msg.sender?

msg.sender represents the address of the account that calls the function.

Q5. What is require() in Solidity?

require() checks a condition. If the condition is false, the transaction is reverted.

Q6. What is the difference between public and private?
- public: Accessible externally and internally.
- private: Accessible only within the contract in which it is declared.
Q7. What is the difference between view and pure?
- view: Reads contract state but does not modify it.
- pure: Neither reads nor modifies contract state.
Q8. Why do we use uint256 instead of int?

uint256 stores only non-negative numbers and is commonly used for balances and amounts.

Q9. Can anyone check the bank balance?

Yes. The balance variable is public, and the getBalance() function is also public.

Q10. Can anyone deposit or withdraw money?

No. Only the owner can deposit or withdraw because of the require(msg.sender == owner) condition.
*/