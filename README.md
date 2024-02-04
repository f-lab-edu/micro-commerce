# micro-commerce

<br/><br/>

# 🗂️ 목차
- [micro-commerce](#micro-commerce)
- [🗂️ 목차](#️-목차)
- [📜 프로젝트 개요](#-프로젝트-개요)
- [📑 시스템 구성도](#-시스템-구성도)
- [🖥 프로젝트 기술 스택](#-프로젝트-기술-스택)
- [🎭 유저 시나리오](#-유저-시나리오)
    - [1. 비회원 시나리오](#1-비회원-시나리오)
      - [회원가입](#회원가입)
      - [비회원 상품 주문](#비회원-상품-주문)
    - [2. 회원 시나리오](#2-회원-시나리오)
      - [회원 상품 주문](#회원-상품-주문)
- [📄 ERD](#-erd)
    - [회원 서비스](#회원-서비스)
    - [상품 서비스](#상품-서비스)
    - [판매자 및 관리자 서비스](#판매자-및-관리자-서비스)
    - [주문 서비스](#주문-서비스)
    - [판매자/관리자 서비스](#판매자관리자-서비스)
- [🌲 Git Branch Strategy](#-git-branch-strategy)

<br/><br/>

# 📜 프로젝트 개요
본 프로젝트는 간단한 기능을 가진 오픈 마켓을 개발합니다.
쇼핑몰을 이용하는 사람들은 **GUEST**, **USER**, **SELLER**로 나눠집니다.

- GUEST는 인증되지 않은 사용자로 상품 주문 시 별도의 비회원 주문 방식으로 처리됩니다.
- USER는 인증된 사용자로 상품 주문 시 
- SELLER는 판매자로 판매할 상품 관리, 주문 관리, 재고 관리, CS 관리 등의 역할이 있습니다.

<br/><br/>

# 📑 시스템 구성도

![system architecture](https://github.com/f-lab-edu/micro-commerce/assets/66265199/8c1c1f42-f373-4d2e-bec0-8941af8b5888)

<br/><br/>


# 🖥 프로젝트 기술 스택

- Java
- SpringBoot
- Spring Cloud Gateway
- Kafka
- Redis
- MySQL
- AWS

<br/><br/>

# 🎭 유저 시나리오
대표 케이스만 기입되어 있습니다. 모든 케이스를 확인하려면 [WIKI-유저 시나리오](https://github.com/f-lab-edu/show-your-worth/wiki/03.-Use-Case---%EC%9C%A0%EC%A0%80-%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4)를 참고 바랍니다.
### 1. 비회원 시나리오
#### 회원가입
1. 회원가입 버튼을 클릭한다.
2. 아이디, 비밀번호, 비밀번호 확인, 전화번호, 이메일을 입력받는다.
3. 입력받은 데이터가 형식에 맞는지 검증한다.
4. 전화번호 인증을 진행한다.
5. 이메일 인증을 진행한다.
6. 인증이 모두 정상적으로 진행되면 가입 완료 메시지를 보여준다.

#### 비회원 상품 주문
1. 상품 페이지 또는 장바구니에서 주문하기 버튼을 누른다.
2. 주문자의 정보, 배송지 정보, 비회원 주문 조회 시 비밀번호, 결제 정보를 입력받는다.
3. 전화번호 인증을 진행한다.
4. 입력한 결제 정보대로 결제를 진행한다.
5. 결제 종류에 따라 결제 대기중/주문 완료 메시지를 보여준다.
6. 판매자는 사용자의 주문 정보를 받아 상품을 준비한다.

<br/>

### 2. 회원 시나리오
#### 회원 상품 주문
1. 상품 페이지 또는 장바구니에서 주문하기 버튼을 누른다.
2. 저장된 배송/결제 정보를 선택하거나 직접 입력 받는다.
3. 입력한 결제 정보대로 결제를 진행한다.
4. 결제 종류에 따라 결제 대기중/주문 완료 메시지를 보여준다.
5. 판매자는 사용자의 주문 정보를 받아 상품을 준비한다.

<br/><br/>

# 📄 ERD
### 회원 서비스
![image](https://github.com/f-lab-edu/micro-commerce/assets/66265199/683434c5-faa2-4b74-ac29-a3c145086f7f)

<br/>

### 상품 서비스
![image](https://github.com/f-lab-edu/micro-commerce/assets/66265199/78eac7cd-ec1c-4d0d-bc1a-bc8bd7463c95)

### 판매자 및 관리자 서비스


 <br/>

### 주문 서비스
![image](https://github.com/f-lab-edu/micro-commerce/assets/66265199/3212a2a9-0c95-4305-9bd5-014c430d59b2)

<br>

### 판매자/관리자 서비스
![image](https://github.com/f-lab-edu/micro-commerce/assets/66265199/79df997e-9fea-4f5c-8dbd-3018e6a9d85e)

<br/><br/>


# 🌲 Git Branch Strategy
![](https://github.com/f-lab-edu/show-your-worth/assets/11500877/68605232-e78f-4ff3-88cb-9cbbba9943c0)
