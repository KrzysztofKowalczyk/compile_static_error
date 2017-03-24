After migrating to new version of Vert.x - 3.4.1 I start getting compile errors on one line, but they are not happening always. 
Sometimes they are on every "clean compileTestGroovy", sometimes no error is thrown. Different code cause different frequency of errors (I think so, hard to test).

- I tried different version of gradle from 2.14 to 3.4.1
- I tried different version of groovy from 2.4.8 to 2.4.10
- I tried Mac and Linux machines

In every case the error exists (more or less often)

I tried workaround (i.e. casting, compile dynamic), but then I have NPE during runtime so it looks like the code is still badly generated.


Code causing a problem:
```
@CompileStatic
class Wrapper {
    io.vertx.ext.web.RoutingContext context

    // I keep constructor, because sometimes if I remove it I can't reproduce the error
    Wrapper(io.vertx.ext.web.RoutingContext context) {
        this.context = context
    }

    // this method fails to compile
    // it was random before, but after removing everything else from the project it fails
    // 95% of time 'clean compileTestGroovy' for me
    Map getBodyAsJson() {
        context.getBodyAsJson().map
    }

}
```

Where RoutingContext have method:
```
@Nullable JsonObject getBodyAsJson();
```

And JsonObject have method:
```
public Map<String, Object> getMap() {
    return map;
}
```
