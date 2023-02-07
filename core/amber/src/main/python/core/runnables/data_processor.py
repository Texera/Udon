import sys
import time
from threading import Event

from loguru import logger

from core.architecture.managers import Context
from core.models import Tuple
from core.util import Stoppable
from core.util.console_message.replace_print import replace_print
from core.util.runnable.runnable import Runnable


class DataProcessor(Runnable, Stoppable):
    def __init__(self, context: Context):
        self._running = Event()
        self._context = context

    def run(self) -> None:
        with self._context.tuple_processing_manager.context_switch_condition:
            self._context.tuple_processing_manager.context_switch_condition.wait()
        self._running.set()
        self._switch_context()
        self.run_speed_log = open(f"run_speed"
                                  f"{self._context.operator_manager.root.name}.csv",
                                  "w")
        while self._running.is_set():
            self.process_tuple()
            self._switch_context()

    def process_tuple(self) -> None:
        finished_current = self._context.tuple_processing_manager.finished_current
        while not finished_current.is_set():

            try:
                logger.info(self._context.statistics_manager.get_statistics()[0])
                start_time = time.time()

                # plain for loop:
                if self._context.statistics_manager.get_statistics()[0] == 10:
                    m = self._context.operator_manager.operator_module_name
                    self._context.debug_manager.put_debug_command(
                        f"b {m}:14, 'doesnotexist' in tuple_['text'] and s == -1")
                    self._switch_context()

                # # udf1:
                # if self._context.statistics_manager.get_statistics()[0] == 10:
                #     m = self._context.operator_manager.operator_module_name
                #     self._context.debug_manager.put_debug_command(
                #         f"b {m}:20, 'doesnotexist' in tuple_['text'] and item == -1")
                #     self._switch_context()

                # # udf2:
                # if self._context.statistics_manager.get_statistics()[0] == 10:
                #     m = self._context.operator_manager.operator_module_name
                #     self._context.debug_manager.put_debug_command(
                #         f"b {m}:14, 'doesnotexist' in tuple_['text'] and pos == -1")
                #     self._switch_context()

                # # udf3:
                # if self._context.statistics_manager.get_statistics()[0] == 10:
                #     m = self._context.operator_manager.operator_module_name
                #     self._context.debug_manager.put_debug_command(
                #         f"b {m}:13, 'doesnotexist' in tuple_['text'] and doc['positive'] == "
                #         f"-1")
                #     self._switch_context()

                # # op1-impl1
                # if isinstance(
                #         self._context.tuple_processing_manager.current_input_tuple,
                #         Tuple
                # ) :
                #     if 'doesnotexist' not in \
                #         self._context.tuple_processing_manager.current_input_tuple[
                #             'text']:
                #
                #         self._context.debug_manager.debugger.do_disable("1")
                #     else:
                #         self._context.debug_manager.debugger.do_enable("1")
                #

                # #op2-impl2
                # if isinstance(
                #         self._context.tuple_processing_manager.current_input_tuple,
                #         Tuple
                # ) :
                #     if 'doesnotexist' not in \
                #         self._context.tuple_processing_manager.current_input_tuple[
                #             'text']:
                #
                #         self._context.debug_manager.debugger.clear_all_breaks()
                #         # logger.info(self._context.debug_manager.debugger.breaks)
                #
                #     else:
                #         logger.info("add breakpoint back")
                #         m = self._context.operator_manager.operator_module_name
                #         self._context.debug_manager.debugger.do_break(
                #             f"{m}:13, 'doesnotexist' in tuple_['text'] and doc["
                #             f"'positive'] == -1")

                operator = self._context.operator_manager.operator
                tuple_ = self._context.tuple_processing_manager.current_input_tuple
                link = self._context.tuple_processing_manager.current_input_link

                # bind link with input index
                # TODO: correct this with the actual port information.
                if link not in self._context.tuple_processing_manager.input_link_map:
                    self._context.tuple_processing_manager.input_links.append(link)
                    index = len(self._context.tuple_processing_manager.input_links) - 1
                    self._context.tuple_processing_manager.input_link_map[link] = index
                port = self._context.tuple_processing_manager.input_link_map[link]

                output_iterator = (
                    operator.process_tuple(tuple_, port)
                    if isinstance(tuple_, Tuple)
                    else operator.on_finish(port)
                )
                with replace_print(self._context.console_message_manager.print_buf):
                    for output in output_iterator:
                        self._context.tuple_processing_manager.current_output_tuple = (
                            None if output is None else Tuple(output)
                        )
                        self._switch_context()

                # current tuple finished successfully
                finished_current.set()
                self.run_speed_log.write(f"{(time.time() - start_time) * 1000}\n")
            except Exception as err:
                logger.exception(err)
                self._context.exception_manager.set_exception_info(sys.exc_info())
            finally:
                self._switch_context()

    def _switch_context(self) -> None:
        """
        Notify the MainLoop thread and wait here until being switched back.
        """
        with self._context.tuple_processing_manager.context_switch_condition:
            self._context.tuple_processing_manager.context_switch_condition.notify()
            self._context.tuple_processing_manager.context_switch_condition.wait()
        self._post_switch_context_checks()

    def _check_and_process_debug_command(self) -> None:
        """
        If a debug command is available, invokes the debugger from this frame.
        """
        while self._context.debug_manager.has_debug_command():
            # Let debugger trace from the current frame.
            # This line will also trigger cmdloop in the debugger.
            # This line has no side effects on the current debugger state.
            self._context.debug_manager.debugger.set_trace()

    def _post_switch_context_checks(self):
        self._check_and_process_debug_command()

    def stop(self):
        self._running.clear()
        self.run_speed_log.close()
