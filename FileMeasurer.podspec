Pod::Spec.new do |spec|
    spec.name                     = 'FileMeasurer'
    spec.version                  = '0.1.0'
    spec.homepage                 = 'https://github.com/alextamariz616/file_measurer_lib'
    spec.source                   = { :git => 'https://github.com/alextamariz616/file_measurer_lib.git', :tag => spec.version.to_s }
    spec.authors                  = 'alextamariz616'
    spec.license                  = { :type => 'MIT', :text => 'LICENSE'}
    spec.summary                  = 'Some description for a Kotlin/Native module'
    spec.vendored_frameworks      = 'build/cocoapods/framework/FileMeasurer.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '11.0'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => '',
        'PRODUCT_MODULE_NAME' => 'FileMeasurer',
    }
                
    spec.script_phases = [
        {
            :name => 'Build FileMeasurer',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end