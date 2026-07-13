from __future__ import annotations

import argparse
import re
import subprocess
import tempfile
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
README = ROOT / "README.md"


def problem_directories() -> list[Path]:
    return sorted(path for path in ROOT.iterdir() if path.is_dir() and re.match(r"^\d{4}-", path.name))


def render_readme() -> str:
    problems = problem_directories()
    java_count = sum(1 for path in problems if next(path.glob("*.java"), None))
    sql_count = sum(1 for path in problems if next(path.glob("*.sql"), None))
    rows = [
        "# LeetCode Solutions",
        "",
        "Automatically indexed Java and SQL solutions. Each solution remains in its LeetHub-generated problem directory.",
        "",
        f"- Problems: **{len(problems)}**",
        f"- Java: **{java_count}**",
        f"- SQL: **{sql_count}**",
        "",
        "| # | Problem | Language |",
        "|---:|---|---|",
    ]
    for path in problems:
        number, slug = path.name.split("-", 1)
        language = "Java" if next(path.glob("*.java"), None) else "SQL" if next(path.glob("*.sql"), None) else "Other"
        rows.append(f"| {int(number)} | [{slug.replace('-', ' ').title()}]({path.name}/) | {language} |")
    rows.extend(["", "Run `python scripts/repository_index.py --check --compile` before pushing.", ""])
    return "\n".join(rows)


def compile_java_solutions() -> None:
    for path in problem_directories():
        source = next(path.glob("*.java"), None)
        if source is None:
            continue
        with tempfile.TemporaryDirectory() as directory:
            target = Path(directory) / "Solution.java"
            target.write_text("import java.util.*;\n" + source.read_text(encoding="utf-8"), encoding="utf-8")
            result = subprocess.run(["javac", "-encoding", "UTF-8", str(target)], capture_output=True, text=True)
            if result.returncode:
                raise SystemExit(f"{source.relative_to(ROOT)} failed to compile:\n{result.stderr}")


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--check", action="store_true")
    parser.add_argument("--compile", action="store_true")
    args = parser.parse_args()
    expected = render_readme()
    if args.check:
        current = README.read_text(encoding="utf-8") if README.exists() else ""
        if current != expected:
            raise SystemExit("README.md is stale. Run: python scripts/repository_index.py")
    else:
        README.write_text(expected, encoding="utf-8")
    if args.compile:
        compile_java_solutions()
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
