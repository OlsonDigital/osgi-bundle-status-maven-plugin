package com.icfolson.maven.plugins.osgibundlestatus.models

import com.fasterxml.jackson.annotation.JsonProperty

class BundleStatus {

    @JsonProperty("data")
    List<Bundle> bundles
}
