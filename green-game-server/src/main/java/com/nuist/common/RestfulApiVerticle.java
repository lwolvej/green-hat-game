package com.nuist.common;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.CorsHandler;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author LwolveJ
 */
public class RestfulApiVerticle extends AbstractVerticle {

    protected Completable createHttpServer(Router router, String host, int port) {
        return vertx.createHttpServer()
                .requestHandler(router::accept)
                .rxListen(port, host)
                .toCompletable();
    }

    protected void enableCrossSupport(Router router) {
        Set<String> allowHeaders = new HashSet<>();
        allowHeaders.add("x-requested-with");
        allowHeaders.add("Access-Control-Allow-Origin");
        allowHeaders.add("origin");
        allowHeaders.add("Content-Type");
        allowHeaders.add("accept");
        router.route().handler(CorsHandler.create("*")
                .allowedHeaders(allowHeaders)
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.DELETE)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.PUT)
        );
    }

    protected void sendResponse(RoutingContext context, Completable asyncResult) {
        HttpServerResponse response = context.response();
        if (asyncResult == null) {
            internalError(context, "invalid_status");
        } else {
            asyncResult.subscribe(response::end, ex -> internalError(context, ex));
        }
    }

    protected void sendResponse(RoutingContext context, Completable asyncResult, Consumer<RoutingContext> f) {
        if (asyncResult == null) {
            internalError(context, "invalid_status");
        } else {
            asyncResult.subscribe(() -> f.accept(context), ex -> internalError(context, ex));
        }
    }

    protected <T> void sendResponse(RoutingContext context, Single<T> asyncResult,
                                    Function<T, String> converter, BiConsumer<RoutingContext, String> f) {
        if (asyncResult == null) {
            internalError(context, "invalid_status");
        } else {
            asyncResult.subscribe(r -> f.accept(context, converter.apply(r)),
                    ex -> internalError(context, ex));
        }
    }

    protected <T> void sendResponse(RoutingContext context, Single<T> asyncResult, Function<T, String> converter) {
        if (asyncResult == null) {
            internalError(context, "invalid_status");
        } else {
            asyncResult.subscribe(r -> ok(context, converter.apply(r)),
                    ex -> internalError(context, ex));
        }
    }

    protected <T> void sendResponse(RoutingContext context, Maybe<T> asyncResult, Function<T, String> converter) {
        if (asyncResult == null) {
            internalError(context, "invalid_status");
        } else {
            Single<Optional<T>> single = asyncResult.map(Optional::of)
                    .switchIfEmpty(Maybe.just(Optional.empty()))
                    .toSingle();
            sendResponseOpt(context, single, converter);
        }
    }

    protected <T> void sendResponseOpt(RoutingContext context, Single<Optional<T>> asyncResult, Function<T, String> converter) {
        if (asyncResult == null) {
            internalError(context, "invalid_status");
        } else {
            asyncResult.subscribe(r -> {
                if (r.isPresent()) {
                    ok(context, converter.apply(r.get()));
                } else {
                    notFound(context);
                }
            }, ex -> internalError(context, ex));
        }
    }

    protected void notFound(RoutingContext context) {
        context.response().setStatusCode(404)
                .putHeader("content-type", "application/json")
                .end(new JsonObject()
                        .put("success", false)
                        .put("data", "")
                        .put("msg", "data not found")
                        .encodePrettily()
                );
    }

    protected void ok(RoutingContext context) {
        context.response().end();
    }

    protected void ok(RoutingContext context, String content) {
        context.response().setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(content);
    }

    protected void create(RoutingContext context, String content) {
        context.response().setStatusCode(201)
                .putHeader("content-type", "application/json")
                .end(content);
    }

    protected void noContent(RoutingContext context) {
        context.response().setStatusCode(204).end();
    }

    protected void internalError(RoutingContext context, String cause) {
        context.response().setStatusCode(500)
                .putHeader("content-type", "application/json")
                .end(new JsonObject()
                        .put("success", false)
                        .put("data", "")
                        .put("msg", cause)
                        .encodePrettily()
                );
    }

    protected void internalError(RoutingContext context, Throwable ex) {
        context.response().setStatusCode(500)
                .putHeader("content-type", "application/json")
                .end(new JsonObject()
                        .put("success", false)
                        .put("data", "")
                        .put("msg", ex.getMessage())
                        .encodePrettily()
                );
    }

    protected void badRequest(RoutingContext context, Throwable ex) {
        context.response().setStatusCode(400)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("error", ex.getMessage()).encodePrettily());
    }

    protected void badRequest(RoutingContext context) {
        context.response().setStatusCode(400)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("error", "bad_request").encodePrettily());
    }

    protected void serviceUnavailable(RoutingContext context) {
        context.fail(503);
    }

    protected void serviceUnavailable(RoutingContext context, Throwable ex) {
        context.response().setStatusCode(503)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("error", ex.getMessage()).encodePrettily());
    }

    protected void serviceUnavailable(RoutingContext context, String cause) {
        context.response().setStatusCode(503)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("error", cause).encodePrettily());

    }
}

