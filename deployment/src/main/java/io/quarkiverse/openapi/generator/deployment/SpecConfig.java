package io.quarkiverse.openapi.generator.deployment;

import java.nio.file.Path;

public class SpecConfig {

    private static final String BUILD_TIME_CONFIG_PREFIX = "quarkus.openapi-generator.codegen";
    public static final String API_PKG_SUFFIX = ".api";
    public static final String MODEL_PKG_SUFFIX = ".model";
    public static final String BUILD_TIME_SPEC_PREFIX_FORMAT = BUILD_TIME_CONFIG_PREFIX + ".spec.\"%s\"";
    private static final String BASE_PACKAGE_PROP_FORMAT = "%s.base-package";
    private static final String SKIP_FORM_MODEL_PROP_FORMAT = "%s.skip-form-model";

    public static String getResolvedBasePackageProperty(final Path openApiFilePath) {
        return String.format(BASE_PACKAGE_PROP_FORMAT, getBuildTimeSpecPropertyPrefix(openApiFilePath));
    }

    public static String getSkipFormModelPropertyName(final Path openApiFilePath) {
        return String.format(SKIP_FORM_MODEL_PROP_FORMAT, getBuildTimeSpecPropertyPrefix(openApiFilePath));
    }

    /**
     * Gets the config prefix for a given OpenAPI file in the path.
     * For example, given a path like /home/luke/projects/petstore.json, the returned value is
     * `quarkus.openapi-generator."petstore.json"`.
     */
    public static String getBuildTimeSpecPropertyPrefix(final Path openApiFilePath) {
        return String.format(BUILD_TIME_SPEC_PREFIX_FORMAT, getEscapedFileName(openApiFilePath));
    }

    private static String getEscapedFileName(final Path openApiFilePath) {
        final String uriFilePath = openApiFilePath.toUri().toString();
        return uriFilePath.substring(uriFilePath.lastIndexOf("/") + 1);
    }
}
