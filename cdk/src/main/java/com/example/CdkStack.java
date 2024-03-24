package com.example;

import software.amazon.awscdk.*;
import software.amazon.awscdk.services.codedeploy.LambdaDeploymentConfig;
import software.amazon.awscdk.services.codedeploy.LambdaDeploymentGroup;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.lambda.*;
import software.constructs.Construct;

import java.util.List;

public class CdkStack extends Stack {
    public CdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        var fns = lambdas().stream()
                .map(e -> {
                    var app = e.name;
                    var handlerFn = e.handler;

                    var jar = String.format("../%s/target/%s.jar", app, e.jar);
                    var fnName = app + "-fn";

                    final FunctionProps fnProps = FunctionProps.builder()
                            .functionName(fnName)
                            .runtime(Runtime.JAVA_21)
                            .architecture(Architecture.X86_64) // SnapStart is not currently supported on Arm_64
                            .code(Code.fromAsset(jar))
                            .handler(handlerFn)
                            .memorySize(1024)
                            .snapStart(SnapStartConf.ON_PUBLISHED_VERSIONS)
                            .timeout(Duration.seconds(15))
                            .build();

                    final Function function = new Function(this, fnName, fnProps);

                    final String aliasName = String.format("%sAlias", fnName);
                    final Alias alias = Alias.Builder.create(this, aliasName)
                            .aliasName(aliasName)
                            .version(function.getCurrentVersion())
                            .build();

                    final String deploymentGroupId = String.format("%sDeploymentGroup", fnName);
                    LambdaDeploymentGroup.Builder.create(this, deploymentGroupId)
                            .alias(alias)
                            .deploymentConfig(LambdaDeploymentConfig.ALL_AT_ONCE)
                            .build();

                    return function;
                })
                .toList();
    }

    private List<FnDef> lambdas() {
        return List.of(
                new FnDef("dagger2", "dagger2-1.0-SNAPSHOT",
                        "com.example.FunctionRequestHandler::handleRequest"),
                new FnDef("manual-di", "manual-di-1.0-SNAPSHOT",
                        "com.example.FunctionRequestHandler::handleRequest"),
                new FnDef("micronaut", "micronaut-0.1",
                        "com.example.FunctionRequestHandler"),
                new FnDef("spring-cloud-functions", "spring-cloud-functions-1.0-SNAPSHOT-aws",
                        "org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest")
        );
    }

    private record FnDef(String name, String jar, String handler) {}
}
