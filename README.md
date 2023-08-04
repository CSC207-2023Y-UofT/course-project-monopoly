# Monopoly: Adventure at UofT

- This software serves as the project for the course CSC207 at the University of Toronto.

- **Distribution without permission is strongly prohibited**

# Functionalities and Specifications

- Our application will utilize a fully implemented Swing GUI, 
where users can log in to the application and start recording bill entries into the database.
- Users will be able to obtain simple statistics on the current entries recorded in the bill.
- Within each recorded bill entry, users can provide additional details on the payment. 
- Users can also utilize this application to record a split bill with other people.

# Code Organization

- We mainly organized our code by layers. However, we also split some "big" layers into fractions by responsibility.
- Folder `data` contains the csv data files.
- Package `entities` contains all the entities we designed.
- Package `usecases` contains different use cases. To make the code easier to search, there are three sub-package in `usecases` .
  - The `generator` package contains use cases that are responsible for generating entities or attributes of entities.
  - The `impactor` package contains use cases that are responsible for changing the attributes of the `player` and the `property`
  - The `interactor` package contains use cases that refers to the actions taken for each subclass of block.

- Package `controller` contains the controllers that are responsible for initializing every details of the game and generating game logics.
- The `Main` class is the entry point of our program. This function calls all the functions in the `controllers` to initialize the game, interact with the human players in a loop, and detect the winner when the loop ends. 

# Test Coverage

Currently, the up-to-date test coverage is:

### controller & main

| Element                   | Class, %  | Method, %  | Line, %     |
| ------------------------- | --------- | ---------- | ----------- |
| `GameController`          | 100%(1/1) | 80% (8/10) | 81% (53/65) |
| `InitController`          | 100%(1/1) | 100% (1/1) | 97% (39/40) |
| `InteractivePanelAdapter` | 100%(1/1) | 100% (1/1) | 66% (4/6)   |
| `Main`                    |           |            |             |

##### related pull request

- https://github.com/CSC207-2023Y-UofT/course-project-monopoly/pull/60

### entity

| Element         | Class, %  | Method, %   | Line, %     |
| --------------- | --------- | ----------- | ----------- |
| `Block`         | 100%(1/1) | 80% (8/10)  | 81% (53/65) |
| `Destiny`       | 100%(1/1) | 80% (4/5)   | 71% (5/7)   |
| `DestinyCard`   | 100%(1/1) | 100% (4/4)  | 100% (8/8)  |
| `ExamCenter`    | 100%(1/1) | 66% (2/3)   | 50% (2/4)   |
| `GameData`      | 100%(1/1) | 75% (3/4)   | 75% (15/20) |
| `Player`        | 100%(1/1) | 76% (10/13) | 83% (5/6)   |
| `Property`      | 100%(1/1) | 94% (16/17) | 93% (28/30) |
| `StartingPoint` | 100%(1/1) | 80% (4/5)   | 83% (5/6)   |
| `TTCStation`    | 100%(1/1) | 66% (2/3)   | 50% (2/4)   |

##### related pull request

- https://github.com/CSC207-2023Y-UofT/course-project-monopoly/pull/45
- https://github.com/CSC207-2023Y-UofT/course-project-monopoly/pull/61
- https://github.com/CSC207-2023Y-UofT/course-project-monopoly/pull/67

### use cases - generator

| Element                    | Class, %  | Method, % | Line, %     |
| -------------------------- | --------- | --------- | ----------- |
| `DestinyCardPoolGenerator` | 100%(1/1) | 100%(1/1) | 92% (23/25) |
| `PropertyGenerator`        | 100%(1/1) | 100%(1/1) | 91% (22/24) |

##### related pull request

- https://github.com/CSC207-2023Y-UofT/course-project-monopoly/pull/45
- https://github.com/CSC207-2023Y-UofT/course-project-monopoly/pull/61

### use cases - impactor

| Element            | Class, %  | Method, %  | Line, %      |
| ------------------ | --------- | ---------- | ------------ |
| `MoneyImpactor`    | 100%(1/1) | 100%(2/2)  | 100% (7/7)   |
| `PositionImpactor` | 100%(1/1) | 100%(2/2)  | 89% (17/19)  |
| `PropertyImpactor` | 100%(1/1) | 50%(1/2)   | 50% (1/2)    |
| `StatusImpactor`   | 100%(1/1) | 100% (3/3) | 100% (12/12) |

##### related pull request

- https://github.com/CSC207-2023Y-UofT/course-project-monopoly/pull/62

### use cases - interactor

| Element             | Class, % | Method, % | Line, % |
| ------------------- | -------- | --------- | ------- |
| `DestinyInteractor` |          |           |         |
| `ECInteractor`      |          |           |         |
| `PPTinteractor`     |          |           |         |
| `TTCInteractor`     |          |           |         |

##### related pull request

- 

### other use cases

| Element                | Class, %   | Method, %  | Line, %      |
| ---------------------- | ---------- | ---------- | ------------ |
| `DestinyCardChooser`   | 100% (1/1) | 100% (1/1) | 100% (5/5)   |
| `DestomuCardExecutor`  | 100% (1/1) | 100% (1/1) | 100% (29/29) |
| `OwnerIdentifier`      | 100% (1/1) | 100% (1/1) | 100% (1/1)   |
| `OwnerPropertyUseCase` | 100% (1/1) | 100% (1/1) | 95% (44/46)  |
| `OwnerPropertyUseCase` | 100% (1/1) | 100% (1/1) | 100% (1/1)   |
| `PasserbyUseCase`      | 100% (1/1) | 100% (1/1) | 100% (6/6)   |
| `StartingPointUseCase` | 100% (1/1) | 100% (1/1) | 100% (2/2)   |
| `StatusChecker`        | 100% (1/1) | 100% (1/2) | 100% (4/4)   |

##### related pull request

- https://github.com/CSC207-2023Y-UofT/course-project-monopoly/pull/45
- https://github.com/CSC207-2023Y-UofT/course-project-monopoly/pull/61
- https://github.com/CSC207-2023Y-UofT/course-project-monopoly/pull/67

### presenters

| Element           | Class, %  | Method, %  | Line, %    |
| ----------------- | --------- | ---------- | ---------- |
| `GameBoard`       | 0%(0/4)   | 0% (0/11)  | 0% (0/30)  |
| `GameMain`        | 0%(0/1)   | 0% (0/4)   | 0% (0/6)   |
| `GameMapPanel`    | 100%(1/1) | 100% (1/1) | 100% (1/1) |
| `InputPresenter`  | 100%(1/1) | 100% (1/1) | 100% (8/8) |
| `OutputPresenter` | 100%(1/1) | 100% (5/5) | 100% (8/8) |
| `PlayerInfoPanel` | 100%(1/1) | 100% (2/2) | 100% (7/7) |

# Code Style and Documentation

- The whole project completely follows a good and health convention. The readability of our codes are great. Some examples are
    - We used `lowerCamelCase` for variable and method names.
    - We used `CONSTANT_CASE` for `static final` constants.
    - We used `CamelCase` for class names.
    - We added `this.` before referring every instance variable and method.
- We also added many java docs for most of the project. 
    - For each classes, we clearly labeled the description of this class.
    - For most methods that need a complete java doc, we added clear explanations of what the method is doing and related resources to check.
    - For most instance variables, we also added many java docs to make sure others understand their meaning.
- We have managed to eliminate most warnings from IntelliJ Idea. For those warnings that are not necessary to eliminate, we have clearly documented them.

# Use of GitHub Features

## Issues

- We have utilized issues in conjunction with pull requests. 
- For each bug fix, feature request, or other improvements, we firstly submit an issue to outline the need to fix this problem and an initial solution of the problem, and then start working on the problem. We distributed assignees to ensure that there are no conflicts in collaboration. We also utilized labels and projects to better mange our progress and works.
- After getting done with some codes and completing a draft, we will make a pull request about this. The pull request will be connected to the issue.
- Then, after merging the pull request into main, the issue will be automatically closed.

## Pull Request

- In addition to what we mentioned in the Issues section, we all participated in reviewing the changes of each other and left many useful comments. This largely helped the growth of our project.

## Project

- Our project keeps track of our work progress so far. 
- We utilized the project to ensure better efficiency.

## Actions

### Auto Grading: Running Tests

- By default, the GitHub classroom checks does not work normally with Java 17 and the version of Gradle that we chose to use. 
- Therefore, we have managed to modify `build.gradle` to allow tests to be ran when we build it using GitHub Actions. 

# Design Patterns



# Clean Architecture

# SOLID

1. **Single Responsibility Principle**: Every class in our project has only one responsibility. For example, 
    
2. **Open and Closed Principle**: 
3. **Liskov Substitution Principle**: 
4. **Interface Segregation Principle**: 
5. **Dependency Inversion Principle**: 

# Standards

## Database Standards

### Related to the destiny card

Table Specifications:

| # Column | Expected Data Type | Description                                                  |
| -------- | ------------------ | ------------------------------------------------------------ |
| A        | `String`           | The message of the destiny card                              |
| B        | `Integer`          | The money the player can get                                 |
| C        | `Integer`          | The distance of the relative move / the ID of the block should go if the data is more than 100 |
| D        | `Integer`          | The number of round the player should stop (represent by negative integer) |

### Related to the property

Table Specifications:

| # Column | Expected Data Type | Description                                                  |
| -------- | ------------------ | ------------------------------------------------------------ |
| A        | `Integer`          | The ID of the property                                       |
| B        | `String`           | The name of the property                                     |
| C - G    | `Integer`          | The prices of the property corresponding to level 0-4 respectively |
| H - L    | `Integer`          | The taxes of the property corresponding to level 1-5 respectively |

### Related to other special blocks

Table Specifications:

| # Column | Expected Data Type | Notes                                                        |
| -------- | ------------------ | ------------------------------------------------------------ |
| A        | `Integer`          | The ID of the block                                          |
| B        | `String`           | The name of the block                                        |
| C        | `Integer`          | The initial bonus value of the starting point (only the line for starting point block has this column) |

# About Us

Group Members:

- Xinyue Li [hecateli](https://github.com/hecateli)
- Aiwei Yin [AlwynYin](https://github.com/AlwynYin)
- Qingyang Bao [snow-QingYang](https://github.com/snow-QingYang)
- Noah Kong [Noahkong12](https://github.com/Noahkong12)
- Zhuochen Du [duduGrassUoft](https://github.com/duduGrassUoft)
- Yuyang Zhao [NeverSoGoodStart](https://github.com/NeverSoGoodStart)
