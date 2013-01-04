package com.citytechinc.cqlibrary.bundlestatusplugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.citytechinc.cqlibrary.bundlestatusplugin.checker.BundleStatusChecker;
import com.citytechinc.cqlibrary.bundlestatusplugin.checker.FelixBundleStatusChecker;

/**
 * Check the status of bundle(s) in an OSGi container.
 */
@Mojo(name = "status", defaultPhase = LifecyclePhase.VERIFY)
public class OsgiBundleStatusPluginMojo extends AbstractMojo {

    /**
     * Symbolic names of OSGi bundles to check.
     */
    @Parameter(required = true)
    private String[] bundleNames;

    /**
     * OSGi container host name.
     */
    @Parameter(defaultValue = "localhost")
    private String host;

    /**
     * OSGi container password.
     */
    @Parameter(defaultValue = "admin")
    private String password;

    /**
     * OSGi container port number.
     */
    @Parameter(defaultValue = "4502")
    private String port;

    /**
     * Required status for bundle(s) being checked. Defaults to "Active".
     */
    @Parameter(defaultValue = "Active")
    private String requiredStatus;

    /**
     * Delay in milliseconds before retrying bundle status check.
     */
    @Parameter(defaultValue = "1000")
    private Integer retryDelay;

    /**
     * Number of times to retry checking bundle status before aborting.
     */
    @Parameter(defaultValue = "5")
    private Integer retryLimit;

    /**
     * Skip execution of the plugin.
     */
    @Parameter(property = "osgi.bundle.status.skip", defaultValue = "false")
    private boolean skip;

    /**
     * OSGi container user name.
     */
    @Parameter(defaultValue = "admin")
    private String user;

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (skip) {
            getLog().info("Skipping execute per configuration.");
        } else {
            final BundleStatusChecker checker = FelixBundleStatusChecker.createFromMojo(this);

            for (final String bundleName : bundleNames) {
                checker.checkStatus(bundleName);
            }
        }
    }

    public String getHost() {
        return host;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    public String getRequiredStatus() {
        return requiredStatus;
    }

    public Integer getRetryDelay() {
        return retryDelay;
    }

    public Integer getRetryLimit() {
        return retryLimit;
    }

    public boolean isSkip() {
        return skip;
    }

    public String getUser() {
        return user;
    }
}