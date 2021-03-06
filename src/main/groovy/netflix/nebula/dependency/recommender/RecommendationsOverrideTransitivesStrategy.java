package netflix.nebula.dependency.recommender;

import org.gradle.api.artifacts.Dependency;
import org.gradle.api.artifacts.DependencyResolveDetails;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsOverrideTransitivesStrategy extends RecommendationStrategy {

    private List<String> firstOrderDepsWithVersions = new ArrayList<>();

    @Override
    public void inspectDependency(Dependency dependency) {
        if (dependency.getVersion() != null && !dependency.getVersion().isEmpty()) {
            firstOrderDepsWithVersions.add(dependency.getGroup() + ":" + dependency.getName());
        }
    }

    @Override
    public boolean recommendVersion(DependencyResolveDetails details, String version) {
        if (version != null && !firstOrderDepsWithVersions.contains(getCoord(details))) {
            details.useVersion(version);
            return true;
        }
        return false;
    }
}
