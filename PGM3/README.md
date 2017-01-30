PGM3
===

Run project in Wildfly 9.2 and Java 8.

Access http://localhost:8080/PGM3/api to endpoints.

**POST -** http://localhost:8080/PGM3/api/person

Body information:

```

{
	"name": "Pedro",
	"email": "pedro-hos@outlook.com",
	"birthdate": "24-12-1990"
}

```

And this return 201 - CREATED

**GET -** http://localhost:8080/PGM3/api/person/{id}

And this return

```

{
	"id": 1,
	"name": "Pedro",
	"email": "pedro-hos@outlook.com",
	"birthdate": "24-12-1990"
}

```