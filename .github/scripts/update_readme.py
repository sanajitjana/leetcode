import os
from datetime import datetime
import subprocess
import pytz  # Requires: pip install pytz

README_PATH = "README.md"

# Count only .java files in Easy, Medium, Hard
def count_problems():
    count = 0
    for folder in ["Easy", "Medium", "Hard"]:
        if os.path.exists(folder):
            for file in os.listdir(folder):
                if os.path.isfile(os.path.join(folder, file)) and file.endswith(".java"):
                    count += 1
    return count

# Get last commit date in human-readable IST format
def last_updated():
    result = subprocess.run(
        ["git", "log", "-1", "--format=%cd", "--date=iso"],
        capture_output=True,
        text=True
    )
    utc_time_str = result.stdout.strip()
    utc_time = datetime.fromisoformat(utc_time_str)

    # Convert to IST
    ist = pytz.timezone("Asia/Kolkata")
    ist_time = utc_time.astimezone(ist)

    # Return formatted date
    return ist_time.strftime("%d %b %Y, %I:%M %p IST")

# Update README.md with the latest values
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
