# 📱 ChatApp - QuickChat Messaging System

## 📋 Project Information

| **Item** | **Details** |
|----------|-------------|
| **Author** | KD MACATE |
| **Student Number** | ST10449398 |
| **Module Code** | PROG5121 |
| **Assignment** | POE Part 3 (Final) |
| **Module Name** | Programming 1A |
| **Institution** | ROSEBANK INTERNATIONAL |
| **Date** | 2026 |

---

## 📖 Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Part 1: Registration & Login](#part-1-registration--login)
4. [Part 2: Sending Messages](#part-2-sending-messages)
5. [Part 3: Store Data & Display Reports](#part-3-store-data--display-reports)
6. [Technology Stack](#technology-stack)
7. [Testing](#testing)
8. [References](#references)
9. [License](#license)

---

## 🎯 Project Overview

**ChatApp** is a Java-based messaging application developed as part of the PROG5121 Programming 1A POE. The application provides a complete user registration, login, messaging system, and data management features with a focus on clean code, comprehensive testing, and professional software development practices.

### Key Objectives:
- ✅ Implement OOP principles
- ✅ Create testable and maintainable code
- ✅ Use version control with Git and GitHub
- ✅ Implement unit testing with JUnit
- ✅ Automate tests using GitHub Actions
- ✅ Store data using JSON file format
- ✅ Implement arrays for data management
- ✅ Create search and delete functionality

---

## ✨ Features

### Completed Features:
- ✓ User registration with validation
- ✓ Secure login system
- ✓ Message creation and sending
- ✓ Recipient cell phone validation
- ✓ Message length checking (max 250 characters)
- ✓ Auto-generated Message IDs (10-digit)
- ✓ Auto-generated Message Hashes
- ✓ JSON file storage for messages
- ✓ Arrays for sent, stored, and disregarded messages
- ✓ Search messages by recipient
- ✓ Search messages by ID
- ✓ Delete messages by hash
- ✓ Display longest stored message
- ✓ Full report generation
- ✓ Menu-driven interface
- ✓ Comprehensive unit tests (34 tests)

---

## 🔐 Part 1: Registration & Login

### Registration Requirements
Users must register with the following details:
- **Username:** Must contain underscore (_) and be ≤ 5 characters
- **Password:** Must contain 8+ chars, 1 capital, 1 number, 1 special character
- **Cell Phone Number:** South African format (+27 followed by 9 digits)

### Validation Rules

| Field | Valid Example | Invalid Example |
|-------|--------------|-----------------|
| Username | `kyl_1` | `kyle123` |
| Password | `Ch&sec@ke99!` | `password` |
| Cell Number | `+27838968976` | `0834567890` |

### Login Process
- Users enter username and password
- System verifies against stored credentials
- Maximum 3 login attempts allowed
- Success message: *"Welcome [first name], [last name] it is great to see you again"*

---

## 💬 Part 2: Sending Messages

### Welcome Screen
After successful login, users see:
==================================================
Welcome to QuickChat
==================================================
How many messages do you wish to enter? _

### Message Structure

| Field | Description | Generation Method |
|-------|-------------|-------------------|
| **Message ID** | 10-digit random number | Auto-generated |
| **Message Number** | Sequential counter | Loop counter |
| **Recipient** | SA cell number (+27 format) | User input + validation |
| **Message Text** | Max 250 characters | User input |
| **Message Hash** | Format: `XX:Y:FIRSTLAST` | Auto-generated using substring |
| **Status** | sent / stored / disregarded | User selection |

### Message Hash Format
The message hash follows this pattern:
[First 2 digits of Message ID]:[Message Number]:[First Word][Last Word]
Example: 00:0:HITONIGHT?

### Menu Options
--- MENU ---
Option 1) Send Messages
Option 2) Show recently sent messages
Option 3) Stored Messages
Option 4) Quit

### Message Actions

| Option | Action | Response |
|--------|--------|----------|
| 1 | Send Message | "Message successfully sent" |
| 2 | Disregard Message | "Press 0 to delete the message" |
| 3 | Store Message | "Message successfully stored" + JSON save |

---

## 📊 Part 3: Store Data & Display Reports

### Arrays Implemented

| Array | Contents |
|-------|----------|
| Sent Messages | Contains all messages sent |
| Disregarded Messages | Contains all messages that were disregarded |
| Stored Messages | Messages loaded from JSON file |
--- STORED MESSAGES MENU ---
a) Display sender and recipient of all stored messages
b) Display the longest stored message
c) Search for a message by ID
d) Search for all messages by recipient
e) Delete a message by hash
f) Display full report
g) Back to main menu

### Features Explained

| Feature | Description |
|---------|-------------|
| **Display Sender & Recipient** | Shows all stored messages with sender and recipient info |
| **Longest Message** | Finds and displays the longest stored message |
| **Search by ID** | Finds a specific message using its unique ID |
| **Search by Recipient** | Finds all messages sent to a specific recipient |
| **Delete by Hash** | Removes a message using its unique hash value |
| **Full Report** | Displays complete details of all stored messages |

---

## 🛠 Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | JDK 17 | Core programming language |
| **JUnit** | JUnit 5.9.0 | Unit testing framework |
| **JSON-java** | 20230227 | JSON file storage |
| **Git** | Latest | Version control |
| **GitHub** | N/A | Remote repository hosting |
| **GitHub Actions** | N/A | CI/CD automation |
| **NetBeans** | 12.0+ | IDE |

---

## 🧪 Testing

### Test Results

| Test Class | Tests Run | Status |
|------------|-----------|--------|
| LogInTest | 14 | ✅ All Passing |
| MessageServiceTest | 11 | ✅ All Passing |
| MessageStorageTest | 9 | ✅ All Passing |
| **TOTAL** | **34** | **✅ ALL PASSING** |

### Test Coverage Includes:
- ✓ Username validation (4 tests)
- ✓ Password complexity (4 tests)
- ✓ Cell phone validation (4 tests)
- ✓ Login functionality (2 tests)
- ✓ Message length (2 tests)
- ✓ Recipient validation (2 tests)
- ✓ Message hash generation (2 tests)
- ✓ Message options (3 tests)
- ✓ Array population (2 tests)
- ✓ Search functionality (2 tests)
- ✓ Delete functionality (1 test)
- ✓ Report generation (2 tests)

---

## 📚 References

For a complete list of references, see [REFERENCES.md](REFERENCES.md)

### Key References:
- **Oracle Java Documentation** - Regex patterns for validation
- **JSON-java Library** - JSON implementation for message storage
- **JUnit 5 Documentation** - Unit testing framework
- **Git Documentation** - Version control system
- **GitHub Actions Docs** - CI/CD pipeline automation

---

## 📝 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

| **License Type** | MIT License |
|-----------------|-------------|
| **Author** | KD MACATE |
| **Student Number** | ST10449398 |
| **Module** | PROG5121 - Programming 1A |
| **Assignment** | POE Part 3 (Final) |
| **Year** | 2026 |
| **Usage** | Educational purposes only |

### You are free to:
- ✅ Use this code for learning purposes
- ✅ Reference this code in academic work
- ✅ Share this code with attribution

### You may NOT:
- ❌ Submit this code as your own work
- ❌ Use for commercial purposes without permission
- ❌ Remove attribution to the original author

---

## 🙏 Acknowledgments

- Module instructors for guidance and support
- Oracle for Java documentation
- Open source community for JSON library
- GitHub for free student developer pack
- Stack Overflow community for technical solutions

---

## 📧 Contact

| **Author** | KD MACATE |
|------------|-----------|
| **Student Number** | ST10449398 |
| **Module** | PROG5121 - Programming 1A |
| **Assignment** | POE Part 3 (Final) |
| **Institution** | ROSEBANK INTERNATIONAL |

---

## ✅ Completion Checklist

- [x] Part 1: Registration and Login Feature
- [x] Part 2: Sending Messages Feature
- [x] Part 3: Store Data and Display Reports
- [x] Arrays for message management
- [x] Search by recipient functionality
- [x] Search by ID functionality
- [x] Delete by hash functionality
- [x] Longest message display
- [x] Full report generation
- [x] Unit Tests (34 tests covering all functionality)
- [x] Git Version Control Setup
- [x] GitHub Repository Created
- [x] GitHub Actions CI/CD Pipeline
- [x] JSON Storage Implementation
- [x] README Documentation Complete
- [x] References Document Created
- [x] LICENSE File Added
- [x] .gitignore Configured

---

**Last Updated:** 2026
**Version:** 3.0
**Status:** ✅ Complete and Fully Functional

---

## 🎓 Declaration

I hereby declare that this project is my original work. All external libraries and references have been properly cited. This project was completed as part of PROG5121 Programming 1A POE Part 3 (Final).

**Signed:** KD MACATE

**Student Number:** ST10449398

**Date:** 2026
| Message Hashes | Contains all message hashes |
| Message IDs | Contains all message IDs |

### Stored Messages Menu Options
