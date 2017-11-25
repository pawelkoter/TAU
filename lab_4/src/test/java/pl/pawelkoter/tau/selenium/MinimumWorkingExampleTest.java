package pl.pawelkoter.tau.selenium;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MinimumWorkingExampleTest {

    @Test
    public void test() {
        assertThat( true ).as( "This is minimum example for" +
                " running two projects on Travis from the same repository" )
                                 .isTrue();
    }
}