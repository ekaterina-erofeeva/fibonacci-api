
## Installation

Run application
```bash
  mvn clean install
  mvn spring-boot:run
```

## Running Tests

To run tests, run the following command

```bash
  mvn test
```

## API Reference

#### Get a sequence of first **n** Fibonacci Numbers

```http
  GET /fibonacciSeries/first/${n}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `n` | `int` | Results for n = [1, 93] |

#### Get the **n**th Fibonacci number

```http
  GET /fibonacciSeries/${n}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `n`      | `int` | Results for n = [1, 93] |

#### Check if **n** is a Fibonacci Number

```http
  GET /fibonacciSeries/validate/${n}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `n`      | `long` | Results for n = [0, 7540113804746346429] |

