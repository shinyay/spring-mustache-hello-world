# Spring and {{ Mustache }} 

---
## Prerequisites

- Java: 1.8.0_181
- Spring Boot: 1.4.7.RELEASE

### Spring Dependencies
- Mustache
- Actuator
- Cloud Security
- Test
- DevTools

## Tutorial
### シンプルな Mustache アプリケーション
#### Controller

まず "/" アクセスした時に `Index` ページを表示する Controller クラスを準備します。

```java
@Controller
class HomeController {
  @GetMapping("/")
  String home() {
    return "index";
  }
}
```

#### Index Page

Index ページではシンプルに `Hello World` を表示するのみの単純なアプリケーションとします。

```html
{{>header}}
<h1>Demo</h1>
<div class="container">Hello World</div>
{{>footer}}
```

#### Mustache 記法 - Partials

Index ページの中で、次のような記述をしています。

- `{{>header}}`
- `{{>footer}}`

これは、タグの中で示している名前のMustacheファイルの内容に置換する記述の仕方です。
上記の場合は、 別に作成している `header.html` と `footer.html` の記述内容が index.html に取り込まれます。

<details><summary>header.html</summary>

```
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Demo Application</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width" />
    <base href="/" />
    <link rel="stylesheet" type="text/css"
          href="/webjars/bootstrap/css/bootstrap.min.css" />
    <script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
    <script type="text/javascript"
            src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
```
</details>

<details><summary>footer.html</summary>

```
</body>
</html>
```
</details>

