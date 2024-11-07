
### Log File:
# log file will be located in core/log directory, with the name:
# "Worker-{execution_id}-{operator_id}-{worker_id}-{test_id}-factor{factor_size}.csv"
# E.g., Worker-Execution6-PythonUDFV2-operator-d659fc8a-5be3-4810-a3a6-442911b152c6-main-0-W4-factor1-ratio0.1.csv
# and execution_id = "Execution6"
#     operator_id = "PythonUDFV2-operator-d659fc8a-5be3-4810-a3a6-442911b152c6"
#     worker_id = "main-0"
#     test_id = "W4"
#     factor_size = 1 // only relevant for W6-related tests
#     ratio = 0.1 // only relevant for W6-related tests

# By default, it records a row of log entry every 1,000 tuples (configured by self.tuple_interval)
# Each log entry contains the following columns:
# 1. tuple_count, how many input tuples are processed when this row is logged.
# 2. udf_time, UDF execution time in seconds elapsed from the previous log entry.
# 3. accumulated_udf_time, the accumulated UDF execution time in seconds.
# 4. accumulated_breakpoint_check_time, the accumulated breakpoint check time in seconds.

### Test runs:
# Run a test with debugger: run a workflow (e.g., W3) with simulated breakpoints by setting `self.test` to its corresponding test id (e.g., "W3"), you will obtain a log file with debugger.
# Run a test without debugger: run the workflow without any breakpoints by setting `self.test` to "no-debug", you will obtain a log file without debugger.

### Detailed Instruction for each figure:
# Table 1:
# Workflow execution time (s): Get workflow runtime from UI, the time next to the Run button after a workflow completes.
# With debugger UDF execution time (s): Get the last accumulated_udf_time from log file with debugger.
# Without debugger UDF execution time (s): Get the last accumulated_udf_time from log file without debugger.

# Figure 18:
# Time for UDF code (s): Get the last accumulated_udf_time from log file without debugger.
# Time for Breakpoint Checks (s): Get the accumulated_breakpoint_check_time from log file with debugger.
# Time for Condition Evaluation (s): accumulated_udf_time from log file with debugger - Time for UDF code (s) - Time for Breakpoint Checks (s).

# Figure 19:
# No debugger time: Get the last accumulated_udf_time from log file without debugger.
# Pdb time = Disable Opt1 + Disable Opt2, Get the accumulated_udf_time from log file with debugger.
# Enable Opt1, Opt2, or both, from debugger_manager.py, Get the accumulated_udf_time from log file with debugger.

# Figure 20:
# Enable Opt1 and Opt2 from debugger_manager.py
# Change self.tuple_interval to an ideal number (i.e., 200K),
# Compare udf_time from log file with debugger and udf_time from log file without debugger.

# Figure 21:
# Enable Opt1 and Opt2 from debugger_manager.py
# Change worker number (thread) from UI on the UDF.
# Compare udf_time from log file with debugger and udf_time from log file without debugger.\

# Figure 23:
# Use W6.
# Disable Opt1 and Opt2.
# Change self.state_size_factor and self.test accordingly.
# Compare the last accumulated_udf_time from log file with debugger.
# No Transfer = no-debug time.

# Figure 24:
# Use W6.
# Disable Opt1 and Opt2.
# Set self.test to "W6Passive".
# Change self.state_size_factor and self.ratio accordingly.
# Compare the last accumulated_udf_time from log file with debugger.

# Figure 26:
# Use W6.
# Disable Opt1 and Opt2.
# Set self.test to "W6Active".
# Change self.state_size_factor accordingly.
# On the UI, adding different numbers of dummy operators.
# Compare the last accumulated_udf_time from log file with debugger.

class UdonExperimentManager:

    def __init__(self, worker_id, main_loop):
        self.worker_id = worker_id
        self.main_loop = main_loop

        self.tuple_interval = 1000

        # change from ["no-debug", "W1", "W2", "W3", "W4", "W5", "W6Passive", "W6Active"]
        self.test = "W1"

        # change from 1, 10, 100, for W6-related tests
        self.state_size_factor = 1

        # change from 0-1, for W6-related tests
        self.ratio = 1

    def set_log_file_name(self, worker):
        worker_id_with_execution = worker.replace(':WF', '-Execution')
        self.log_file_name = f"../log/{worker_id_with_execution}-{self.test}-factor{self.state_size_factor}-ratio{self.ratio}.csv"

    def start_logging(self):
        self.log_output = open(self.log_file_name, 'w')
        self.log_output.write("tuple_count, udf_time, accumulated_udf_time, accumulated_breakpoint_check_time\n")

    def log(self, text):
        self.log_output.write(text)

    def close_log(self):
        self.log_output.close()

    def add_simulated_breakpoint(self):
        if self.test == "W1":
            # For W1:
            self.main_loop.simulate_debug_command(
                "b 50, 'hello' in tuple_['text'] and 'welcome' in tokens"
            )

        elif self.test == "W2":
            # For W2:
            self.main_loop.simulate_debug_command(
                "b 26, 'hello' in tuple_['text'] and 'welcome' in tokens"
            )

        elif self.test == "W3":
            # For W3:
            self.main_loop.simulate_debug_command(
                "b 9, tuple_['column-5'] > 10"
            )

        elif self.test == "W4":
            # For W4:
            if "fc88c19f-1f8a-4c7e-8578-8272952c47c8" not in self.worker_id:  # only break for Image Rotator
                self.main_loop.simulate_debug_command(
                    "b 18, H > 400"
                )

        elif self.test == "W5":
            # For W5:
            self.main_loop.simulate_debug_command(
                "b 23, sum(1 for token in doc if token.pos_ == 'NOUN') > 3"
            )

        elif "W6" in self.test:
            # For state transfer between multiple operators
            upstream = "PythonUDFV2-operator-21e8857b-8c1b-4cc0-9b88-03026d791e2a-main-0"
            downstream = "PythonUDFV2-operator-e6e9797a-fe9d-444a-bc43-1f28f42b9f03-main-0"

            if "Passive" in self.test:
                # For W6 passive transfer (Store state + Request State)

                if upstream in self.worker_id:
                    self.main_loop.simulate_debug_command(
                        f"ss 51 tokens {self.ratio}"
                    )
                if downstream in self.worker_id:
                    self.main_loop.simulate_debug_command(
                        f"rs 17 51 tokens {self.worker_id.split('-')[0]}-{upstream}"
                    )
            else:
                # For W6 active transfer (Append State):
                if upstream in self.worker_id:
                    self.main_loop.simulate_debug_command(
                        "as 51 tokens"
                    )
