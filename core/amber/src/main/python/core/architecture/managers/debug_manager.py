from threading import Event

from core.models.single_blocking_io import SingleBlockingIO


class DebugManager:
    def __init__(self, condition):
        self.talk_with_debugger: bool = False
        self.debug_in = SingleBlockingIO(condition)
        self.debug_out = SingleBlockingIO(condition)

    def has_debug_command(self) -> bool:
        return self.debug_in.value is not None

    def has_debug_event(self) -> bool:
        return self.debug_out.value is not None

    def get_debug_event(self) -> str:
        return self.debug_out.readline()

