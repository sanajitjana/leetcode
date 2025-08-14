import os
import subprocess
from datetime import datetime

README_PATH = "README.md"

# Count problems
def count_problems():
    count = 0
    for folder in ["Easy", "Medium", "Hard"]:
        if os.path.exists(folder):
            for file in os.listdir(folder):
                if os.path.isfile(os.path.join(folder, file)):
                    count += 1
    return count

# Get last commit date (latest in repo)
def last_updated():
    result = subprocess.run(
        ["git", "log", "-1", "--format=%cd", "--date=iso"],
        capture_output=True,
        text=True
    )
    # Convert to human readable format
    dt = datetime.fromisoformat(result.stdout.strip())
    return dt.strftime("%d %b %Y, %I:%M %p IST")

def update_readme():
    with open(README_PATH, "r", encoding="utf-8") as f:
        content = f.read()

    problems_count = count_problems()
    updated_time = last_updated()

    # Replace only inside the Progress section
    import re
    content = re.sub(
        r"(## 🏆 Progress\s+Problems solved:).*",
        f"## 🏆 Progress\n\nProblems solved: {problems_count}\nLast updated: {updated_time}",
        content,
        count=1,
        flags=re.DOTALL
    )

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(content)

if __name__ == "__main__":
    update_readme()
