import os
from datetime import datetime
import subprocess

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

# Get last commit date
def last_updated():
    result = subprocess.run(
        ["git", "log", "-1", "--format=%cd", "--date=iso"],
        capture_output=True,
        text=True
    )
    return result.stdout.strip()

def update_readme():
    with open(README_PATH, "r", encoding="utf-8") as f:
        content = f.read()

    problems_count = count_problems()
    updated_time = last_updated()

    content = content.replace(
        "Problems solved: <!--PROBLEMS_COUNT-->",
        f"Problems solved: {problems_count}"
    )
    content = content.replace(
        "Last updated: <!--LAST_UPDATED-->",
        f"Last updated: {updated_time}"
    )

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(content)

if __name__ == "__main__":
    update_readme()
