# libnavannotation / libnavcompiler Demo

此文档演示如何在项目中使用自定义注解 **libnavannotation** 及其编译期处理器 **libnavcompiler** 生成页面导航配置文件 `destination.json`。

## 1. 引入依赖

在需要使用的模块（例如 `app`）的 `build.gradle` 中声明：

```gradle
// 处理自定义注解生成代码
annotationProcessor project(':libnavcompiler')
// 注解定义所在的库
implementation project(':libnavannotation')
```

确保模块启用了 Java 8 支持：

```gradle
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```

## 2. 在页面上标记注解

在 `Activity` 或 `Fragment` 类上使用 `@ActivityDestination` 或 `@FragmentDestination` 描述该页面的路由信息：

```java
@FragmentDestination(pageUrl = "main/tabs/demo", asStarter = false, needLogin = false)
public class DemoFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_demo, container, false);
    }
}
```

`pageUrl` 作为页面唯一标识，不可重复；`needLogin` 表示是否要求登录；`asStarter` 用于指定应用启动后的默认页面。

## 3. 编译生成 navigation 配置

执行 `./gradlew assembleDebug` 等编译任务时，`libnavcompiler` 会扫描项目中所有标记了上述注解的类，并生成 `app/src/main/assets/destination.json` 文件。该文件内容类似：

```json
{
  "main/tabs/demo": {
    "isFragment": true,
    "asStarter": false,
    "needLogin": false,
    "pageUrl": "main/tabs/demo",
    "className": "com.mooc.ppjoke.ui.demo.DemoFragment",
    "id": 123456789
  }
}
```

此 JSON 文件会在运行时由 `NavGraphBuilder` 解析，动态构建 `NavController` 的 `NavGraph`，从而实现页面的统一管理与跳转。

## 4. 运行效果

当 `NavGraphBuilder` 读取到生成的 `destination.json` 后，就能通过指定的 `pageUrl` 进行导航。例如：

```java
NavController navController = Navigation.findNavController(activity, R.id.nav_host);
Bundle args = new Bundle();
navController.navigate("main/tabs/demo", args);
```

这样就完成了自定义注解收集、文件生成到运行时跳转的全部流程。

