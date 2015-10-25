# Java module development cycle using Gradle.

1. Create a new project.

  ```bash
  # mkdir {project-name}
  # cd {project-name}
  # gradle init --type java-library
  ```

2. Coding some logics and tests.
  - source
    - src/main/java/Library.java
    - src/test/java/LibraryTest.java

3. Test above.

  ```bash
  # gradle test
  ```

4. Check the test result. 

  - Upload local gradle test report's resources to AWS S3 static web site host.
    ```bash
    # aws s3 rm s3://hu2-gradle-test-result/ --recursive
    # aws s3 sync ./build/reports/tests/ s3://hu2-gradle-test-result/
    ```

  - Access url below.

    [Gradle test rport](http://hu2-gradle-test-result.s3-website-ap-northeast-1.amazonaws.com/)

5. Build java module.

  ```bash
  # gradle compileJava
  ```

# How to execute just one class.
1. Compile.

  ```bash
  # gradle compileJava
  ```

2. Execute.

  ```bash
  # java -cp build/classes/main Library 
  ```
