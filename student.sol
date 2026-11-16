// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract MarksManagmtSys {
    struct Student {
        uint id;
        string fName;
        string lName;
        uint marks;
    }

    Student[] public stdRecords;
    address owner = msg.sender;

    modifier onlyOwner() {
        require(msg.sender == owner);
        _;
    }

    function addNewRecords(uint id, string memory first,
        string memory last, uint marks) public onlyOwner {
        stdRecords.push(Student(id, first, last, marks));
    }

    function viewMarks(uint index) public view returns (uint) {
        return stdRecords[index].marks;
    }

    function bonusMarks(uint index, uint bonus) public onlyOwner {
        stdRecords[index].marks += bonus;
    }

    receive() external payable {}
    fallback() external payable {}
}

/*
Important Solidity Keywords for Viva
Keyword	Meaning
struct	Creates a user-defined data type
uint	Stores unsigned integer values
string	Stores text
address	Stores Ethereum address
public	Allows public access
modifier	Adds restrictions to functions
require()	Checks a condition
msg.sender	Address of the function caller
memory	Temporary data storage
push()	Adds an element to a dynamic array
view	Reads data without modifying state
returns	Specifies the return type
payable	Allows Ether to be received
receive()	Receives Ether without transaction data
fallback()	Handles unmatched calls


5. Important Viva Questions and Answers
Q1. What is the purpose of this smart contract?

It is used to store student information, view marks and update marks by adding bonus marks.

Q2. What is a structure in Solidity?

A structure is a user-defined data type that groups multiple variables of different data types.

Q3. Why do we use an array?

An array is used to store multiple student records in a single variable.

Q4. What is a modifier?

A modifier is used to apply conditions or restrictions before executing a function.

Q5. What is the purpose of onlyOwner?

It restricts certain operations so that only the contract owner can execute them.

Q6. What is msg.sender?

It represents the address of the account that calls the current function.

Q7. What is the difference between memory and storage?
- memory: Temporary data that exists during function execution.
- storage: Permanent data stored on the blockchain.
Q8. Why is view used in viewMarks()?

Because the function only reads student marks and does not modify the contract's state.

Q9. What is push() in Solidity?

push() adds a new element at the end of a dynamic array.

Q10. What is the difference between receive() and fallback()?

receive() handles Ether transfers without data, whereas fallback() handles unmatched function calls and can also receive Ether.

Q11. Can any user view student marks?

Yes. The viewMarks() function is public, so anyone can call it.

Q12. Can any user add student records?

No. Only the owner can add records because the function uses the onlyOwner modifier.

Q13. What happens if someone other than the owner calls bonusMarks()?

The require() condition fails, and the transaction is reverted.
6. Important Points to Remember for Practical Exam
Remember these five things:
1. Struct – Stores student details.
2. Array – Stores multiple student records.
3. Modifier – Restricts access to the owner.
4. View function – Displays student marks.
5. Bonus function – Updates student marks.
*/