package error

import groovy.transform.CompileStatic

import io.vertx.ext.web.RoutingContext

@CompileStatic
class Wrapper {
    RoutingContext context

    // I keep constructor, because sometimes if I remove it I can't reproduce the error
    Wrapper(RoutingContext context) {
        this.context = context
    }

    // this method fails to compile
    // it was random before, but after removing everything else from the project it fails
    // 95% of time 'clean compileTestGroovy' for me
    Map getBodyAsJson() {
        context.getBodyAsJson().map
    }

}
