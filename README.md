# Loan decision engine

Determines what would be the maximum sum for a loan. Takes in a personal code, loan amount, loan period in months and returns either rejected or approved with the maximum amount.

Runs on Java 25 Spring Boot and Svelte.

I decided on Spring Boot because after researching previous Inbank's software developer applications (wanted to find the tech stack used) and finding that Java was primarily required. Svelte was just chosen because I am familiar and can easily prototype a quick form with it (and Tailwind).

I also went with a layered architecture, which may not be the best choice for a small task like this, however I wanted to showcase my ability to think about future-proofness, if that is a word.

I assumed that the output should be a boolean with the maximum loan amount.

One thing that I would improve about the home assignment is to improve the task description's clarity. It was quite vague on what the expected output should be. However, I understand that this may test my ability to make assumptions in unclear environments. I would improve the assignment by being more concrete on the API endpoint or give a clear warning that it is vague on purpose.

Note: there was AI used on planning and some frontend code structure generation.

## Run

```Bash
    ./backend/gradlew bootRun
    cd ./frontend && bun install && bun run dev
```

Frontend (Svelte) starts on http://localhost:5173 and backend (Spring) on http://localhost:8080