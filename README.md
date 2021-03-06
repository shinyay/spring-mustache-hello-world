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
### 1. シンプルな Mustache アプリケーション

Mustache テンプレートを用いた静的画面を表示するのみのシンプルなアプリケーションを作ります。

#### 1.1. Controller

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

#### 1.2. Index Page

Index ページではシンプルに `Hello World` を表示するのみの単純なアプリケーションとします。

```html
{{>header}}
<h1>Demo</h1>
<div class="container">Hello World</div>
{{>footer}}
```

#### 1.3. Mustache 記法 - Partials

Index ページの中で、次のような記述をしています。

- `{{>header}}`
- `{{>footer}}`

これは、タグの中で示している名前のMustacheファイルの内容に置換する記述の仕方です。
上記の場合は、 別に作成している `header.html` と `footer.html` の記述内容が index.html に取り込まれます。

<details><summary>header.html</summary>

```html
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

```html
</body>
</html>
```
</details>

### 2. Model オブジェクトの利用 - Value

Model オブジェクトを生成し設定した値を Mustache テンプレートで表示するアプリケーションを作ります。

#### 2.1. Mustache 記法 - Variables `{{key}}`

`header.html` と `index.html` で静的に表示設定していた内容を以下のように変更します。

- header.html

```html
<title>{{title}}</title>
```

- index.html

```html
<h1>{{title}}</h1>
```

Model オブジェクトでキー名が `title` として設定された属性の値をそれぞれ表示します。

#### 2.2. Model オブジェクトの設定

Controller クラスの中で Model オブジェクトの設定を行う処理を追加します。

```
@GetMapping
public String home(Model model) {
	model.addAttribute("title", "Mustache Application");
	return "index";
}
```

モデルの属性名を先述のMustache テンプレートで定義したキー名である `title` を設定し、属性値を表示させる文字列 `Mustache Application` を指定しています。

#### 2.3. spring-boot:run

この時点のアプリケーションを起動します。

```
$ ./mvnw clean spring-boot:run -DkipTests -Dmaven.test.skip=true
```

#### 2.4. 動作確認

Curl 及びブラウザからアクセスして動作確認を行います。

```
$ curl -X GET http://localhost:8080
```

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Mustache Application</title>
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

<h1>Mustache Application</h1>
<div class="container">Hello World</div>
</body>
</html>
```

![simple](images/mustache-app-simple.png)

### 3. Model オブジェクトの利用 - List

Model オブジェクトを生成し設定したリストを Mustache テンプレートで表示するアプリケーションを作ります。

#### 3.1. Mustache 記法 - Variables `{{#key}} 〜 {{/key}}`

以下の記法で、key に指定した値がリストの場合、内容を列挙して記述できます。

```
{{#key}}
リストの内容を抜き出す記法
{{/key}}
```

#### 3.2. キー指定せずにリストの内容を列挙

`{{#key}} 〜 {{/key}}` の間に `{{.}}` を挿入して内容を列挙します。

**tags** という属性名を指定し、その属性で追加された値を列挙するテンプレートを用意します。

```
{{#tags}}
{{.}}
{{/tags}}
```

**tags** という属性名に対し、ArrayListで要素を追加したオブジェクトを属性値として Model に登録します。

```
model.addAttribute("tags", new ArrayList<>(Arrays.asList("spring", "springboot", "mustache")));
```

#### 3.3. 動作確認

アプリケーションを起動し、動作確認を行うと以下のようにListに追加した値が列挙され画面表示されています。

![list-value](images/mustache-app-list-value.png)

#### 3.4. キー指定してリストの内容を列挙

`{{#key}} 〜 {{/key}}` の間に `{{キー名}}` を挿入して内容を列挙します。

以下の例では、**itemList** という属性名に対して、属性値に登録している Bean オブジェクト のフィールド(=キー)を getter からアクセスして取得します。

```
{{#itemList}}
{{code}}
{{name}}
{{description}}
{{/itemList}}
```

Mustache テンプレートで指定したキーをフィールドに持つBeanを以下のように定義します。

```
class Item{
		private String code;
		private String name;
		private String description;

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		Item(String code, String name, String description) {
			this.code = code;
			this.name = name;
			this.description = description;
		}
}
```

#### 3.5. 動作確認

アプリケーションを起動し、動作確認を行うと以下のようにListに追加したBeanのフィールドが列挙され画面表示されています。

![list-value](images/mustache-app-beanlist.png)
