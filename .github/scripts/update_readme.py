import os
import subprocess
from datetime import datetime
import re

README_PATH = "README.md"

# Count problems (count problem directories under p folder)
def count_problems():
    count = 0
    folder = "p"
    if os.path.exists(folder):
        for item in os.listdir(folder):
            item_path = os.path.join(folder, item)
            if os.path.isdir(item_path) and not item.startswith('.'):
                count += 1
    return count

# Get last commit date of the repo
def last_updated():
    result = subprocess.run(
        ["git", "log", "-1", "--format=%cd", "--date=iso"],
        capture_output=True,
        text=True
    )
    raw_date = result.stdout.strip()
    try:
        dt = datetime.fromisoformat(raw_date.replace(" ", "T", 1))
    except ValueError:
        # Fallback: parse with strptime
        dt = datetime.strptime(raw_date, "%Y-%m-%d %H:%M:%S %z")
    return dt.strftime("%d %b %Y, %I:%M %p %Z")

def update_readme():
    with open(README_PATH, "r", encoding="utf-8") as f:
        content = f.read()

    problems_count = count_problems()
    updated_time = last_updated()

    # Regex to replace only inside the Progress section
    new_progress = f"## 🏆 Progress\n\n- Problems solved: {problems_count}\n- Last updated: {updated_time}"
    content = re.sub(
        r"## 🏆 Progress\s+(- Problems solved: .*?\n- Last updated: .*)",
        new_progress,
        content,
        flags=re.DOTALL
    )

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(content)

if __name__ == "__main__":
    update_readme()
