package org.challenge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CollaboratorApplicationTests {

    @Test
    public void main() {
        ChallengeApplication.main(new String[]{});
    }
}
