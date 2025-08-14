import os
from datetime import datetime
from zoneinfo import ZoneInfo  # Built-in in Python 3.9+
import re

README_PATH = "README.md"

# Count only .java files inside Easy, Medium, Hard (recursive)
def count_problems():
    count = 0
    for folder in ["Easy", "Medium", "Hard"]:
        if os.path.exists(folder):
            for root, _, files in os.walk(folder):
                for file in files:
                    if file.endswith(".java"):
                        count += 1
    return count

# Get current IST time in human-readable format
def last_updated():
    ist_time = datetime.now(ZoneInfo("Asia/Kolkata"))
    return ist_time.strftime("%d %b %Y, %I:%M %p IST")

# Update README.md placeholders
def update_readme():
    with open(README_PATH, "r", encoding="utf-8") as f:
        content = f.read()

    problems_count = count_problems()
    updated_time = last_updated()

    # Use regex to ensure replacement works even if extra spaces
    content = re.sub(
        r"Problems solved: <!--PROBLEMS_COUNT-->.*",
        f"Problems solved: <!--PROBLEMS_COUNT-->{problems_count}",
        content
    )
    content = re.sub(
        r"Last updated: <!--LAST_UPDATED-->.*",
        f"Last updated: <!--LAST_UPDATED-->{updated_time}",
        content
    )

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(content)

if __name__ == "__main__":
    update_readme()
