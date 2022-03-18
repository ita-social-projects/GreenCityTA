# GreenCityTA

> If you want to take care about our environment and get rid of your wastes, but you are extremely busy and can't get to the sorting station? UBS Courier will come to take your recyclable materials! We provide emergency garbage assistance

**Badges will go here**

- build status
- coverage
- issues (waffle.io maybe)
- license

[![Build Status](https://img.shields.io/travis/ita-social-projects/GreenCity/master?style=flat-square)](https://travis-ci.org/github/ita-social-projects/GreenCity)
[![Coverage Status](https://img.shields.io/gitlab/coverage/ita-social-projects/GreenCity/master?style=flat-square)](https://coveralls.io)
[![Github Issues](https://img.shields.io/github/issues/ita-social-projects/GreenCity?style=flat-square)](https://github.com/ita-social-projects/GreenCity/issues)
[![Pending Pull-Requests](https://img.shields.io/github/issues-pr/ita-social-projects/GreenCity?style=flat-square)](https://github.com/ita-social-projects/GreenCity/pulls)
[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

---

## Table of Contents

- [Installation](#installation)
  - [Required to install](#Required-to-install)
  - [Environment](#Environment)
  - [Clone](#Clone)
  - [Setup](#Setup)
  - [How to run local](#How-to-run-local)
  - [How to run Docker](#How-to-run-Docker)
- [Usage](#Usage)
  - [How to work with swagger UI](#How-to-work-with-swagger-UI)
  - [How to run tests](#How-to-run-tests)
  - [How to Checkstyle](#How-to-Checkstyle)
- [Documentation](#Documentation))
- [Contributing](#contributing)
  - [git flow](#git-flow)
  - [issue flow](#git-flow)
- [FAQ](#faq)
- [Support](#support)
- [License](#license)

---

## Installation

### Required to install
* Java (16.0.2)
* PostgreSQL (42.2.10)
* Selenium (4.1.1)
* TestNG (7.4.0)
* Allure (2.11.2)

### Environment
environmental variables
```properties
baseURL=${BASE_URL}
UbsHomePageURL=${UBS_HOME_PAGE_URL}
baseUbsApiURL=${BASE_UBS_API_URL}
baseUserApiURL=${BASE_USER_API_URK}
localUbsHomePageURL=${LOCAL_UBS_HOME_PAGE_URL}
email=${EMAIL}
userName=${USERNAME}
password=${PASSWORD}
cardNumber=${CARD_NUMBER}
expiryDate=${EXPIRY_DATE}
CVV2=${CVV2}
invalidCardNumber=${INVALID_CARD_NUMBER}
JDBCGreenCityUsername=${JDBC_GREENCITY_USERNAME}
JDBCGreenCityPassword=${JDBC_GREENCITY_PASSWORD}
JDBCGreenCityURL=${JDBC_GREENCITY_URL}
JDBCGreenCityUbsUsername=${JDBC_GREENCITY_UBS_USERNAME}
JDBCGreenCityUbsPassword=${JDBC_GREENCITY_UBS_PASSWORD}
JDBCGreenCityUbsURL=${JDBC_GREENCITY_UBS_URL}
adminEmail=${ADMIN_EMAIL}
adminPassword=${ADMIN_PASSWORD}
passwordForUserData=${PASSWORD_FOR_USERDATA}
emailForUserData=${EMAIL_FOR_USERDATA}
emailForChangePassw=${EMAIL_FOR_CHANGE_PASSW}
passwordForChangepassw=${PASSWORD_FOR_CHANGEPASSW}
passwordHash=${PASSWORD_HASH}
userWithoutOrdersEmail=${USER_WITHOUT_ORDERS_EMAIL}
```

### Clone

- Clone this repo to your local machine using `https://github.com/ita-social-projects/GreenCityTA.git`


---

## Usage
### How to work with swagger UI
- Use the following link to open Swagger UI UBS: https://greencity-ubs.azurewebsites.net/swagger-ui.html#
- Use the following link to open Swagger UI User: https://greencity-user.azurewebsites.net/swagger-ui.html#

### How to run tests

First run:
- `mvn install -Dmaven.test.skip=true` - install
- `mvn test-compile` - compile

Running UI tests
- `mvn test` - run tests, by default using pom maven setup
- `mvn test -Dtestng.xml=*.xml` to execute the automation UI tests via maven.

For example

- `mvn test -Dtestng.xml=testNG.xml` to running all UI tests

---

## Contributing

### Git flow
> To get started...
#### Step 1

- **Option 1**
    - 🍴 Fork this repo!

- **Option 2**
    - 👯 Clone this repo to your local machine using `https://github.com/ita-social-projects/GreenCityTA.git`

#### Step 2

- **HACK AWAY!** 🔨🔨🔨

#### Step 3

- 🔃 Create a new pull request using <a href="https://github.com/ita-social-projects/GreenCityTA/compare/" target="_blank">github.com/ita-social-projects/GreenCityTA</a>.

---

## Team

> Or Contributors/People

[![@roman266](https://avatars3.githubusercontent.com/roman266?s=100&v=4)](https://github.com/roman266)
[![@tetiana-ustymenko](https://avatars3.githubusercontent.com/tetiana-ustymenko?s=100&v=4)](https://github.com/tetiana-ustymenko)
[![@annachopyk](https://avatars3.githubusercontent.com/annachopyk?s=100&v=4)](https://github.com/annachopyk)
[![@lhalam](https://avatars3.githubusercontent.com/lhalam?s=100&v=4)](https://github.com/lhalam) 
[![@zubachihor](https://avatars3.githubusercontent.com/zubachihor?s=100&v=4)](https://github.com/zubachihor)
[![@OlenaKulchytska](https://avatars3.githubusercontent.com/OlenaKulchytska?s=100&v=4)](https://github.com/OlenaKulchytska)
[![@Dinara227](https://avatars3.githubusercontent.com/Dinara227?s=100&v=4)](https://github.com/Dinara227)
[![@SchematicM](https://avatars3.githubusercontent.com/SchematicM?s=100&v=4)](https://github.com/SchematicM)
[![@Levv4ik](https://avatars3.githubusercontent.com/Levv4ik?s=100&v=4)](https://github.com/Levv4ik)
[![@Setupb](https://avatars3.githubusercontent.com/Setupb?s=100&v=4)](https://github.com/Setupb)
[![@vanyahrabarskiy](https://avatars3.githubusercontent.com/vanyahrabarskiy?s=100&v=4)](https://github.com/vanyahrabarskiy)

- You can just grab their GitHub profile image URL
- You should probably resize their picture using `?s=200` at the end of the image URL.

---

## FAQ

- **How do I do *specifically* so and so?**
    - No problem! Just do this.

---

## Support

Reach out to me at one of the following places!

- Website at <a href="https://ita-social-projects.github.io/GreenCityClient/#/ubs">`GreenCityUBS`</a>
- Facebook at <a href="https://www.facebook.com/NowasteUkraine/">`Україна БЕЗ сміття`</a>
- Instagram at <a href="https://www.instagram.com/nowasteukraine/">`nowasteukraine`</a>
- Telegram at <a href="https://t.me/nowasteukraine">`Україна Без Сміття`</a>
- YouTube at <a href="https://www.youtube.com/channel/UChFVBZ_HcaQYiF-GcMHiCNA">`Україна БЕЗ сміття`</a>

---

## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- **[MIT license](http://opensource.org/licenses/mit-license.php)**
- Copyright 2020 © <a href="https://softserve.academy/" target="_blank"> SoftServe IT Academy</a>.