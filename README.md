# 📱 ChatApp - QuickChat Messaging System

## 📋 Project Information

| **Item** | **Details** |
|----------|-------------|
| **Author** | KD MACATE |
| **Student Number** | ST10449398 |
| **Module Code** | PROG5121 |
| **Assignment** | POE Part 2 |
| **Module Name** | Programming 1A |
| **Institution** | ROSEBANK INTERNATIONAL |
| **Date** | 2026 |

---

## 📖 Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Part 1: Registration & Login](#part-1-registration--login)
4. [Part 2: Sending Messages](#part-2-sending-messages)
5. [References](#references)
6. [License](#license)

---

## 🎯 Project Overview

**ChatApp** is a Java-based messaging application developed as part of the PROG5121 Programming 1A POE. The application provides a complete user registration, login, and messaging system with a focus on clean code, comprehensive testing, and professional software development practices.

### Key Objectives:
- ✅ Implement OOP principles
- ✅ Create testable and maintainable code
- ✅ Use version control with Git and GitHub
- ✅ Implement unit testing with JUnit
- ✅ Automate tests using GitHub Actions
- ✅ Store data using JSON file format

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
- ✓ Menu-driven interface
- ✓ Comprehensive unit tests

### Features in Development:
- 🔄 Show recently sent messages (Coming Soon)
- 🔄 Message deletion functionality
- 🔄 Multiple user support

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

### Registration & Login Methods

| Method Name | Functionality |
|-------------|---------------|
| `checkUserName()` | Validates username format |
| `checkPasswordComplexity()` | Validates password strength |
| `checkCellPhoneNumber()` | Validates SA cell number |
| `registerUser()` | Handles user registration |
| `loginUser()` | Verifies login credentials |
| `returnLoginStatus()` | Returns login result message |

---

## 💬 Part 2: Sending Messages

### Welcome Screen
After successful login, users see:
==================================================
Welcome to QuickChat
==================================================
How many messages do you wish to enter? _

text

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

text

### Menu Options
--- MENU ---
Option 1) Send Messages
Option 2) Show recently sent messages
Option 3) Quit

text

### Message Actions

| Option | Action | Response |
|--------|--------|----------|
| 1 | Send Message | "Message successfully sent" |
| 2 | Disregard Message | "Press 0 to delete the message" |
| 3 | Store Message | "Message successfully stored" + JSON save |

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

📚 References
For a complete list of references, see REFERENCES.md

Key References:

-JSON-java Library - JSON implementation for message storage

-JUnit 5 Documentation - Unit testing framework

-Git Documentation - Version control system

-GitHub Actions Docs - CI/CD pipeline automation

📝 License
This project is licensed under the MIT License - see the LICENSE file for details.

License Type	MIT License
Author	KD MACATE
Student Number	ST10449398
Module	PROG5121 - Programming 1A
Assignment	POE Part 2
Year	2026
Usage	Educational purposes only
You are free to:
✅ Use this code for learning purposes

✅ Reference this code in academic work

✅ Share this code with attribution

You may NOT:
❌ Submit this code as your own work

❌ Use for commercial purposes without permission

❌ Remove attribution to the original author

🙏 Acknowledgments
Module instructors for guidance and support

Open source community for JSON library

GitHub for free student developer pack

Stack Overflow community for technical solutions

📧 Contact
Author	KD MACATE
Student Number	ST10449398
Module	PROG5121 - Programming 1A
Assignment	POE Part 2
Institution	ROSEBANK INTERNATIONAL
✅ Completion Checklist
Part 1: Registration and Login Feature

Part 2: Sending Messages Feature

Unit Tests (23 tests covering all functionality)

Git Version Control Setup

GitHub Repository Created

JSON Storage Implementation

README Documentation Complete

References Document Created

LICENSE File Added

.gitignore Configured

Last Updated: 2026
Version: 2.0
Status: ✅ Complete and Fully Functional

🎓 Declaration
I hereby declare that this project is my original work. All external libraries and references have been properly cited. This project was completed as part of PROG5121 Programming 1A POE Part 2.

Signed: KD MACATE
Student Number: ST10449398
Date: 2026
