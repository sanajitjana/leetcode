import os
import re
from datetime import datetime
from zoneinfo import ZoneInfo  # built-in since Python 3.9

README_PATH = "README.md"
FOLDERS = ["Easy", "Medium", "Hard"]
EXT = ".java"


def count_by_folder():
    """Return dict of counts per folder and total, counting EXT files recursively."""
    per = {}
    total = 0
    for folder in FOLDERS:
        c = 0
        if os.path.exists(folder):
            for root, _, files in os.walk(folder):
                for f in files:
                    if f.endswith(EXT):
                        c += 1
        per[folder] = c
        total += c
    per["_total"] = total
    return per


def human_ist_now():
    """Current IST time in human-readable format."""
    ist = datetime.now(ZoneInfo("Asia/Kolkata"))
    return ist.strftime("%d %b %Y, %I:%M %p IST")


def replace_line(content: str, label: str, new_line: str) -> tuple[str, bool]:
    """
    Replace a line starting with the label.
    Works for both placeholder and non-placeholder versions.

    Examples it will match:
      Problems solved: <!--PROBLEMS_COUNT-->3
      Problems solved: 3
      Last updated: <!--LAST_UPDATED-->14 Aug 2025, ...
      Last updated: 14 Aug 2025, ...
    """
    # 1) Prefer placeholder form
    placeholder_patterns = [
        rf"(?mi)^{re.escape(label)}\s*:?\s*<!--PROBLEMS_COUNT-->.*$",
        rf"(?mi)^{re.escape(label)}\s*:?\s*<!--LAST_UPDATED-->.*$",
    ]
    for pat in placeholder_patterns:
        new_content, n = re.subn(pat, new_line, content)
        if n > 0:
            return new_content, True

    # 2) Generic form (no placeholder)
    generic_pat = rf"(?mi)^{re.escape(label)}\s*:?.*$"
    new_content, n = re.subn(generic_pat, new_line, content)
    if n > 0:
        return new_content, True

    return content, False


def ensure_progress_block(content: str, problems_count: int, updated_time: str) -> str:
    """Append a progress block if neither line exists anywhere."""
    block = (
        "\n\n🏆 Progress\n\n"
        f"Problems solved: <!--PROBLEMS_COUNT-->{problems_count}\n"
        f"Last updated: <!--LAST_UPDATED-->{updated_time}\n"
    )
    return content + block


def update_readme():
    counts = count_by_folder()
    total = counts["_total"]
    updated_time = human_ist_now()

    # Debug prints to workflow logs
    print("[update_readme] Counts:", counts)
    print("[update_readme] Total:", total)
    print("[update_readme] Time:", updated_time)

    with open(README_PATH, "r", encoding="utf-8") as f:
        content = f.read()

    # Desired lines
    solved_line = f"Problems solved: <!--PROBLEMS_COUNT-->{total}"
    time_line = f"Last updated: <!--LAST_UPDATED-->{updated_time}"

    changed = False

    content2, changed1 = replace_line(content, "Problems solved", solved_line)
    content = content2
    changed = changed or changed1

    content2, changed2 = replace_line(content, "Last updated", time_line)
    content = content2
    changed = changed or changed2

    if not (changed1 or changed2):
        # Neither line existed — append a fresh block
        print("[update_readme] Progress block not found; appending a new one.")
        content = ensure_progress_block(content, total, updated_time)
        changed = True

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(content)

    # Exit code / message for clarity (not strictly required)
    if changed:
        print("[update_readme] README.md updated.")
    else:
        print("[update_readme] No changes required.")


if __name__ == "__main__":
    update_readme()
