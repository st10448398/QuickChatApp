# 📱 ChatApp - QuickChat Messaging System

## 📋 Project Information

| **Item** | **Details** |
|----------|-------------|
| **Author** | KD MACATE |
| **Student Number** | ST10449398 |
| **Module Code** | PROG5121 |
| **Assignment** | POE Part 2 |
| **Module Name** | Programming 1A |
| **Institution** | [Your Institution Name] |
| **Date** | 2026 |

---

## 📖 Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Part 1: Registration & Login](#part-1-registration--login)
4. [Part 2: Sending Messages](#part-2-sending-messages)
5. [Technology Stack](#technology-stack)
6. [Installation Guide](#installation-guide)
7. [How to Run](#how-to-run)
8. [Testing](#testing)
9. [JSON Storage](#json-storage)
10. [Git & GitHub](#git--github)
11. [CI/CD Pipeline](#cicd-pipeline)
12. [Folder Structure](#folder-structure)
13. [References](#references)
14. [License](#license)

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


