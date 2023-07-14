<h1 align="center">Udon: Efficient Debugging of User-Defined Functions in Big Data Systems with Line-by-Line Control</h1>

<img src="core/new-gui/src/assets/logos/full_logo_small.png" alt="texera-logo" width="100px" height="60px"/> Udon builts on Texera, a collaborative data analytics workflow system.

***

## Udon instruction

Please follow [Guide to Develop Texera](https://github.com/Texera/texera/wiki/Guide-for-Developers) to install the
Udon-enabled Texera, enabling Python UDF.

***

## Udon components

Udon executes Python UDFs with a two-thread model.

- [Data processing thread](https://github.com/Texera/Udon/blob/master/core/amber/src/main/python/core/runnables/data_processor.py)
- [Control processing thread](https://github.com/Texera/Udon/blob/master/core/amber/src/main/python/core/runnables/main_loop.py)

Udon manages the integrated debugger with
a [DebuggerManager](https://github.com/Texera/Udon/blob/master/core/amber/src/main/python/core/architecture/managers/debug_manager.py).
There are the following configurations:

| Parameter name              | Type | Default Value | Usage                                                        |
|-----------------------------|------|---------------|--------------------------------------------------------------|
| PAUSE_ON_HITTING_BREAKPOINT | Bool | False         | Let the debugger halt the execution when hitting a breakpoint. |
| PAUSE_ON_SETTING_BREAKPOINT | Bool | False         | Let the debugger halt the execution when a new breakpoint is set. |
| OP1_ENABLED                 | Bool | False         | Enables OP1, to hot-swap UDF code                            |
| OP2_ENABLED                 | Bool | False         | Enables OP2, to pull up predicates                           |

***

## Play with Udon

Udon supports actual control messages from the controller, which requires the user to manually input debug comments from
the Python UDF console. For demonstration and experiment purposes, we simulate debug instructions inside the control
processing thread. We provided a method `simulate_debug_command` to send arbitrary debug command to the target Python
UDF worker.

### pdb debug commands

Example to add a `setting breakpoint` command at line 20 of the UDF code, with some conditions:

```python
# in main_loop.py:127
self.simulate_debug_command(
    "b 20, 'Udon' in tuple_['text'] and 'welcome' in tokens"
)
```

Udon supports all [pdb commands](https://www.google.com/search?client=safari&rls=en&q=pdb+commands&ie=UTF-8&oe=UTF-8)
except
for [`jump`](https://www.google.com/search?client=safari&rls=en&q=pdb+commands&ie=UTF-8&oe=UTF-8), [`interact`](https://docs.python.org/3/library/pdb.html#pdbcommand-interact)
and [`quit`](https://docs.python.org/3/library/pdb.html#pdbcommand-quit).

### Additional debug commands

In addition to the standard debug commands, Udon also supports state transfer commands, including `ss` (store
state), `rs` (request state), and `as` (append state).
Example to add a `request state` command at line 20 to request the upstream Python worker's state at line 22:

```python
# in main_loop.py:132
self.simulate_debug_command(
    "rs 20 22 tokens "
    "PythonUDFV2-operator-8c277eca-adb7-4b4f-866c-3e8950535ef1-main"
)
```

## Acknowledgements

This project is supported by the <a href="http://www.nsf.gov">National Science Foundation</a> under the
awards [III 1745673](https://www.nsf.gov/awardsearch/showAward?AWD_ID=1745673), [III 2107150](https://www.nsf.gov/awardsearch/showAward?AWD_ID=2107150),
AWS Research Credits, and Google Cloud Platform Education Programs.
