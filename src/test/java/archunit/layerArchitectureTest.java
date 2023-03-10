package archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.ysg.servicetemplate", importOptions = ImportOption.DoNotIncludeTests.class)
class layerArchitectureTest {

    @ArchTest
    static ArchRule layer_dependencies_are_respected = layeredArchitecture()
            .layer("api").definedBy("com.ysg.servicetemplate.api..")
            .layer("Application").definedBy("com.ysg.servicetemplate.application..")
            .layer("Domain").definedBy("com.ysg.servicetemplate.domain..")
            .layer("infrastructure").definedBy("com.ysg.servicetemplate.infrastructure..")

            .whereLayer("api").mayNotBeAccessedByAnyLayer()
            .whereLayer("infrastructure").mayNotBeAccessedByAnyLayer()
            .whereLayer("Application").mayOnlyBeAccessedByLayers("api", "infrastructure")
            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "api", "infrastructure");
}

