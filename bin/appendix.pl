#!/usr/bin/env perl
# Author: James Harris <wopian@wopian.me>
# Package *nix: pp -o bin/appendix bin/appendix.pl
# Package Windows: pp -o bin/appendix.exe bin/appendix.pl

use strict;
use warnings;
use autodie;
use feature qw(say);
use utf8;
use open qw(:std :utf8);
binmode STDOUT, ':encoding(UTF-8)';
use IPC::System::Simple qw(capturex);

my $print_author = 1;
my $commit_count = 1;
my ($git_user, $git_repo, $git_commit_address);
my $git_remote = ( split /\n/xms, capturex(qw(git remote -v)) )[0];

say "%% DO NOT EDIT\n%% *NIX: bin/appendix > report/appendix.tex";
my $git_command_commit_msg = '%s';

say '\begin{tabularx}{\textwidth}{l l l X r r r}
\textbf{\#} & \textbf{Author} & \textbf{Date} & \textbf{Commit Message} & \textbf{Files} & \textbf{++} & \textbf{-{}-} \\\\
\endhead';

my @lines;
my @git_command = qw(git log --grep=feat --date=short --shortstat --no-merges);
my $format = "format:%H & %an No Author& %ad & $git_command_commit_msg";
push(@git_command, qq(--pretty=$format));
@lines = capturex(@git_command);

sub latex_escape {
    my $paragraph = shift;
    $paragraph =~ s/\\/\\backslash/g;
    $paragraph =~ s/([\$\#&%_{}])/\\$1/g;
    $paragraph =~ s/(\^)/\\$1\{\}/g;
    $paragraph =~ s/~/\\~\{\}/g;
    $paragraph =~ s/(\\backslash)/\$$1\$/g;
    return $paragraph;
}

my $which_line = 0;
my @changes;
for (reverse @lines) {
    next if /\A\Z/xms;
    chomp;
    if ($which_line) {
        s/\A([0-9a-f]{40})\s//xms or die "Did not match the commit hash\n";
        my $date_author = '';
        my $c_msg;
        /(?:& )(.*?)No Author(& .*? &) (.*)/;
        $date_author = latex_escape($1) . $2;
        $c_msg       = latex_escape($3);
        say "$commit_count & $date_author $c_msg & " . join( ' & ', @changes ) . ' \\\\';
        $commit_count++;
    }
    else {
        @changes = ( 0, 0, 0 );
        /(\d+) files? changed/ and $changes[0] = $1;
        /(\d+) insertions?/    and $changes[1] = $1;
        /(\d+) deletions?/     and $changes[2] = $1;
    }
    $which_line ^= 1;
}
say '\end{tabularx}';
