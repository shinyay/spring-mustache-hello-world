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
### Simple Spring Boot
#### Controller

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

```html
<!doctype html>
<html lang="en">
  <body>
    <h1>Demo</h1>
    <div>Hello World</div>
  </body>
</html>
```
