# HW1-expr

Разбор математических выражений.
Используемые сущности:

- [`Literal`](src/ru/itmo/idedev/utils/Literal.java) – обозначение для цифры
- [`Variable`](src/ru/itmo/idedev/utils/Variable.java) – переменная
- [`BinaryExpression`](src/ru/itmo/idedev/utils/BinaryExpression.java) – бинарная операция над двумя `Expression`-ми
- [`ParenthesisExpression`](src/ru/itmo/idedev/utils/ParenthesisExpression.java) – выражение внутри круглых скобок

Все сущности имплментирует интерфейс [`Expression`](src/ru/itmo/idedev/utils/Expression.java).


